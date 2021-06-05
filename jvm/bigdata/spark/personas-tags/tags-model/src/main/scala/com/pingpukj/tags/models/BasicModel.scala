package com.pingpukj.tags.models

import com.pingpukj.tags.Constant
import com.pingpukj.tags.meta.HBaseMeta
import com.pingpukj.tags.utils.HBaseTools
import org.apache.hadoop.hbase.client.{Put, Result}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.spark.SparkConf
import org.apache.spark.internal.Logging
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.storage.StorageLevel

/**
 * @Author: chb
 * @Date: 2021/4/23 8:44
 * @E-Mail:
 * @DESC: 标签基类，各个标签模型继承此类，实现其中打标签方法doTag即可
 */
trait BasicModel extends Logging {
  // 变量声明
  var spark: SparkSession = _

  // 1. 初始化：构建SparkSession实例对象
  def init(): Unit = {
    // 1.1. 创建SparkConf,设置应用相关配置
    val sparkConf: SparkConf = new SparkConf()
      .setMaster("local[4]")
      .setAppName(this.getClass.getSimpleName.stripSuffix("$"))
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer") // 设置序列化为：Kryo
      .registerKryoClasses(Array(classOf[ImmutableBytesWritable], classOf[Result], classOf[Put]))
      .set("spark.sql.shuffle.partitions", "4") // 设置Shuffle分区数目

    // 1.2. 建造者模式创建SparkSession会话实例对象
    spark = SparkSession.builder()
      .config(sparkConf)
      .enableHiveSupport() // 启用与Hive集成
      .config("hive.metastore.uris", Constant.HIVE_METASTORE) // 设置与Hive集成: 读取Hive元数据MetaStore服务
      .config("spark.sql.warehouse.dir", Constant.HIVE_WAREHOUSE) // 设置数据仓库目录
      .getOrCreate()
  }


  /**
   * 2. 准备标签数据：依据标签ID从MySQL数据库表tbl_basic_tag获取标签数据
   *
   * @param tagId
   * @return
   */
  def getTagData(tagId: Long): DataFrame = {
    // 2.1. 标签查询语句
    val tagTable =
      s"""
         |(
         |SELECT `id`,
         | `name`,
         | `rule`,
         | `level`
         |FROM `profile_tags`.`tbl_basic_tag`
         |WHERE id = $tagId
         |UNION
         |SELECT `id`,
         | `name`,
         | `rule`,
         | `level`
         |FROM `profile_tags`.`tbl_basic_tag`
         |WHERE pid = $tagId
         |ORDER BY `level` ASC, `id` ASC
         |) AS basic_tag
         |""".stripMargin
    // 2.2. 获取标签数据
    val tagDF: DataFrame = spark.read
      .format("jdbc")
      .option("driver", Constant.SQL_DRIVER)
      .option("url", Constant.SQL_URL)
      .option("dbtable", tagTable)
      .option("user", Constant.SQL_USER)
      .option("password", Constant.SQL_PSWD)
      .load()
    // 2.3. 返回数据
    tagDF

  }


  /**
   * 3. 业务数据：依据业务标签规则rule，从数据源获取业务数据
   *
   * @param tagDF
   * @return
   */
  def getBusinessData(tagDF: DataFrame): DataFrame = {
    import tagDF.sparkSession.implicits._
    // 3.1. 4级标签规则rule
    val tagRule: String = tagDF
      .filter($"level" === 4)
      .head().getAs[String]("rule")
    //logInfo(s"==== 业务标签数据规则: {$tagRule} ====")

    // 3.2. 解析标签规则，先按照换行\n符分割，再按照等号=分割
    val ruleMap: Map[String, String] = tagRule
      .split("\n")
      .map { line =>
        val Array(attrName, attrValue) = line.trim.split("=")
        (attrName, attrValue)
      }
      .toMap

    // 3.3. 依据标签规则中inType类型获取数据
    var businessDF: DataFrame = null
    if ("hbase".equals(ruleMap("inType").toLowerCase())) {
      // 规则数据封装到HBaseMeta中
      val hbaseMeta: HBaseMeta = HBaseMeta.getHBaseMeta(ruleMap)
      // 依据条件到HBase中获取业务数据
      businessDF = HBaseTools.read(
        spark, hbaseMeta.zkHosts, hbaseMeta.zkPort,
        hbaseMeta.hbaseTable,
        hbaseMeta.family,
        hbaseMeta.selectFieldNames.split(",").toSeq
      )
    } else {
      // 如果未获取到数据，直接抛出异常
      new RuntimeException("业务标签未提供数据源信息，获取不到业务数据，无法计算标签")
    }

    // 3.4. 返回数据
    businessDF
  }


  /**
   * 4. 构建标签：依据业务数据和属性标签数据建立标签
   *
   * @param businessDF
   * @param tagDF
   * @return
   */
  def doTag(businessDF: DataFrame, tagDF: DataFrame): DataFrame

  /**
   * 5. 保存画像标签数据至HBase表
   *
   * @param modelDF
   */
  def saveTag(modelDF: DataFrame): Unit = {
    HBaseTools.write(
      modelDF, Constant.ZOOKEEPER_HOSTS, Constant.ZOOKEEPER_port,
      "tbl_profile", "user", "userId"
    )
  }

  /**
   * 6. 关闭资源：应用结束，关闭会话实例对象
   */
  def close(): Unit = {
    if (null != spark) spark.stop()
  }

  /**
   * 规定标签模型执行流程顺序
   *
   * @param tagId
   */
  def executeModel(tagId: Long): Unit = {
    // a. 初始化
    init()
    try {
      // b. 获取标签数据
      val tagDF: DataFrame = getTagData(tagId)
      //basicTagDF.show()
      tagDF.persist(StorageLevel.MEMORY_AND_DISK)
      tagDF.count() // 触发缓存
      // c. 获取业务数据
      val businessDF: DataFrame = getBusinessData(tagDF)
      //businessDF.show()
      // d. 计算标签
      val modelDF: DataFrame = doTag(businessDF, tagDF)
      //modelDF.show()
      // e. 保存标签
      saveTag(modelDF)
      tagDF.unpersist()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      close()
    }
  }
}