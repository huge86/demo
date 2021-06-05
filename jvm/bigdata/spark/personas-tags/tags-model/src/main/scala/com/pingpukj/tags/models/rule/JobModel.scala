package com.pingpukj.tags.models.rule


import com.pingpukj.tags.Constant
import com.pingpukj.tags.meta.HBaseMeta
import com.pingpukj.tags.utils.HBaseTools
import org.apache.hadoop.hbase.client.{Put, Result}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.spark.SparkConf
import org.apache.spark.internal.Logging
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.storage.StorageLevel

/**
 * @Author: chb
 * @Date: 2021/4/21 17:00
 * @E-Mail:
 * @DESC: 用户职业标签模型
 */
object JobModel extends Logging {
  /*
    321 职业
    322 学生 1
    323 公务员 2
    324 军人 3
    325 警察 4
    326 教师 5
    327 白领 6
  */
  def main(args: Array[String]): Unit = {
    // 创建SparkSession实例对象
    val spark: SparkSession = {
      // a. 创建SparkConf,设置应用相关配置
      val sparkConf: SparkConf = new SparkConf()
        .setMaster("local[4]")
        .setAppName(this.getClass.getSimpleName.stripSuffix("$"))
        .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer") // 设置序列化为：Kryo
        .registerKryoClasses(Array(classOf[ImmutableBytesWritable], classOf[Result], classOf[Put]))
        .set("spark.sql.shuffle.partitions", "4") // 设置Shuffle分区数目

      // b. 建造者模式创建SparkSession会话实例对象
      val session = SparkSession.builder()
        .config(sparkConf)
        .enableHiveSupport() // 启用与Hive集成
        .config("hive.metastore.uris", Constant.HIVE_METASTORE) // 设置与Hive集成: 读取Hive元数据MetaStore服务
        .config("spark.sql.warehouse.dir", Constant.HIVE_WAREHOUSE) // 设置数据仓库目录
        .getOrCreate()
      // c. 返回会话对象
      session
    }


    // 1. 依据TagId，从MySQL读取标签数据
    // TODO: 从MySQL数据库读取标签数据（基础标签表：tbl_basic_tag），依据业务标签ID读取
    val tagTable: String =
    """
      |(
      |SELECT `id`,
      | `name`,
      | `rule`,
      | `level`
      |FROM `tags`.`tbl_basic_tag`
      |WHERE id = 14
      |UNION
      |SELECT `id`,
      | `name`,
      | `rule`,
      | `level`
      |FROM `tags`.`tbl_basic_tag`
      |WHERE pid = 14
      |ORDER BY `level` ASC, `id` ASC
      |) AS basic_tag
      |""".stripMargin

    val basicTagDF = spark.read
      .format("jdbc")
      .option("driver", Constant.SQL_DRIVER)
      .option("url", Constant.SQL_URL)
      .option("dbtable", tagTable)
      .option("user", Constant.SQL_USER)
      .option("password", Constant.SQL_PSWD)
      .load()
    basicTagDF.persist(StorageLevel.MEMORY_AND_DISK)
    //basicTagDF.printSchema()
    basicTagDF.show(20, truncate = false)

    import org.apache.spark.sql.functions._
    import spark.implicits._


    // 2、解析标签rule，从HBase读取业务数据
    // TODO: 依据业务标签规则获取业务数据，比如到HBase数据库读取表的数据
    // 2.1 4级标签规则rule
    val tagRule: String = basicTagDF.filter($"level" === 4)
      .head()
      .getAs[String]("rule")
    //logInfo(s"=========== 业务标签的规则rule：{$tagRule} ===========")

    // 2.2 解析标签规则，先按照换行符\n分割，再按照等号分割
    val ruleMap: Map[String, String] = tagRule
      .split("\\n")
      .map { line =>
        val Array(attrName, attrValue) = line.trim.split("=")
        (attrName, attrValue)
      }
      .toMap
    logWarning(s"============ { ${ruleMap.mkString(", ")} } ===========")

    // 2.3 依据标签规则中inType类型获取数据
    var businessDF: DataFrame = null
    if ("hbase".equals(ruleMap("inType").toLowerCase)) {
      // 规则数据封装到HBaseMeta中
      val hbaseMeta: HBaseMeta = HBaseMeta.getHBaseMeta(ruleMap)
      // 依据条件到HBase表中获取业务数据
      businessDF = HBaseTools.read(
        spark, hbaseMeta.zkHosts, hbaseMeta.zkPort,
        hbaseMeta.hbaseTable, hbaseMeta.family,
        hbaseMeta.selectFieldNames.split(",").toSeq
      )
    } else {
      // 如果未获取到数据，直接抛出异常
      new RuntimeException("业务标签未提供数据源信息，获取不到业务数据，无法计算标签")
    }
    //businessDF.printSchema()
    businessDF.show(20, truncate = false)


    // 3. 结合业务数据和属性标签数据，给用户打标签
    // TODO: 业务数据和属性标签结合，构建标签：规则匹配型标签 -> rule match
    // 3.1. 获取5级标签对应TagId和TagRule
    val attrTagRuleMap: Map[String, String] = basicTagDF
      .filter($"level" === 5)
      .select($"rule", $"name")
      .as[(String, String)]
      .rdd
      .collectAsMap().toMap
    //println(attrTagRuleMap)
    val attrTagRuleMapBroadcast = spark.sparkContext.broadcast(attrTagRuleMap)
    // 3.2. 自定义UDF函数, 依据Job职业和属性标签规则进行标签化
    val job_to_tag: UserDefinedFunction = udf(
      (job: String) => attrTagRuleMapBroadcast.value(job)
    )
    val modelDF: DataFrame = businessDF
      .select(
        $"id".as("userId"), //
        job_to_tag($"job").as("job")
      )
    //modelDF.printSchema()
    //modelDF.show(50, truncate = false)
    basicTagDF.unpersist()

    // 4. 5. 保存画像标签数据至HBase表
    // TODO: 将标签数据存储到HBase表中：用户画像标签表 -> tbl_profile
    HBaseTools.write(
      modelDF, Constant.ZOOKEEPER_HOSTS, Constant.ZOOKEEPER_port,
      "tbl_profile", "user", "userId"
    )

    // 关闭资源
    spark.stop()
  }
}
