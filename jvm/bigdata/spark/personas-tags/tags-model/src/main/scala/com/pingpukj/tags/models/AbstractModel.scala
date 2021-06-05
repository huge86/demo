package com.pingpukj.tags.models


import com.pingpukj.tags.ModelType
import com.pingpukj.tags.config.ModelConfig
import com.pingpukj.tags.meta.HBaseMeta
import com.pingpukj.tags.utils.SparkUtils
import org.apache.spark.internal.Logging
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import org.apache.spark.storage.StorageLevel

/**
 * @Author: chb
 * @Date: 2021/4/23 15:29
 * @E-Mail:
 * @DESC: 每个标签模型都有公共属性：标签名称和标签类型，所以定义抽象类 AbstractModel ，
 *        所有标签模型类继承此类，实现标签计算方法 doTag 即可。
 */
abstract class AbstractModel(modelName: String, modelType: ModelType) extends Logging {
  // 设置Spark应用程序运行的用户：root, 默认情况下为当前系统用户
  System.setProperty("user.name", "root")
  System.setProperty("HADOOP_USER_NAME", "root")
  // 变量声明
  var spark: SparkSession = _

  // 1. 初始化：构建SparkSession实例对象
  def init(isHive: Boolean): Unit = {
    spark = SparkUtils.createSparkSession(this.getClass, isHive)
  }

  // 2. 准备标签数据：依据标签ID从MySQL数据库表tbl_basic_tag获取标签数据
  def getTagData(tagId: Long): DataFrame = {
    spark.read
      .format("jdbc")
      .option("driver", ModelConfig.MYSQL_JDBC_DRIVER)
      .option("url", ModelConfig.MYSQL_JDBC_URL)
      .option("dbtable", ModelConfig.tagTable(tagId))
      .option("user", ModelConfig.MYSQL_JDBC_USERNAME)
      .option("password", ModelConfig.MYSQL_JDBC_PASSWORD)
      .load()
  }


  // 3. 业务数据：依据业务标签规则rule，从数据源获取业务数据
  def getBusinessData(tagDF: DataFrame): DataFrame = {
    import tagDF.sparkSession.implicits._
    // 3.a. 4级标签规则rule
    val tagRule: String = tagDF
      .filter($"level" === 4)
      .head().getAs[String]("rule")
    logInfo(s"==== 业务标签数据规则: {$tagRule} ====")

    // 3.b. 解析标签规则，先按照换行\n符分割，再按照等号=分割
    /*
    inType=hbase
    zkHosts=bigdata-cdh01.itcast.cn

    zkPort=2181
    hbaseTable=tbl_tag_users
    family=detail
    selectFieldNames=id,gender
    */
    val ruleMap: Map[String, String] = tagRule
      .split("\n")
      .map { line =>
        val Array(attrName, attrValue) = line.trim.split("=")
        (attrName, attrValue)
      }
      .toMap

    // 3.c. 依据标签规则中inType类型获取数据
    var businessDF: DataFrame = null
    if ("hbase".equals(ruleMap("inType").toLowerCase())) {
      // 规则数据封装到HBaseMeta中
      val hbaseMeta: HBaseMeta = HBaseMeta.getHBaseMeta(ruleMap)

      // 依据条件到HBase中获取业务数据
      /*businessDF = HBaseTools.read(
        spark, hbaseMeta.zkHosts, hbaseMeta.zkPort,
        hbaseMeta.hbaseTable,
        hbaseMeta.family,
        hbaseMeta.selectFieldNames.split(",").toSeq
      )*/

      businessDF = spark.read
        .format("hbase")
        .option("zkHosts", hbaseMeta.zkHosts)
        .option("zkPort", hbaseMeta.zkPort)
        .option("hbaseTable", hbaseMeta.hbaseTable)
        .option("family", hbaseMeta.family)
        .option("selectFields", hbaseMeta.selectFieldNames)
        .option("whereFields", hbaseMeta.filterConditions)
        .load()
    } else {
      // 如果未获取到数据，直接抛出异常
      new RuntimeException("业务标签未提供数据源信息，获取不到业务数据，无法计算标签")
    }

    // 3.d. 返回数据
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


  // 5. 保存画像标签数据至HBase表
  def saveTag(modelDF: DataFrame): Unit = {
    /*HBaseTools.write(
      modelDF, Constant.ZOOKEEPER_HOSTS, Constant.ZOOKEEPER_port, "tbl_profile",
      "user", "userId"
    )*/

    // 使用注册外部数据源来写入hbase
    modelDF.write
      .mode(SaveMode.Overwrite)
      .format("hbase")
      .option("zkHosts", ModelConfig.PROFILE_TABLE_ZK_HOSTS)
      .option("zkPort", ModelConfig.PROFILE_TABLE_ZK_PORT)
      .option("hbaseTable", ModelConfig.PROFILE_TABLE_NAME)
      .option("family", ModelConfig.PROFILE_TABLE_FAMILY_USER)
      .option("rowKeyColumn", ModelConfig.PROFILE_TABLE_ROWKEY_COL)
      .save()
  }

  // 6. 关闭资源：应用结束，关闭会话实例对象
  def close(): Unit = {
    if (null != spark) spark.stop()
  }

  // 规定标签模型执行流程顺序
  def executeModel(tagId: Long, isHive: Boolean = false): Unit = {
    // a. 初始化
    init(isHive)
    try {
      // b. 获取标签数据
      val tagDF: DataFrame = getTagData(tagId)
      //basicTagDF.show()
      tagDF.persist(StorageLevel.MEMORY_AND_DISK).count()
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
