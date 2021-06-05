package com.pingpukj.tags.models.rule

import com.pingpukj.tags.Constant
import com.pingpukj.tags.meta.HBaseMeta
import org.apache.hadoop.hbase.client.{Put, Result}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.spark.SparkConf
import org.apache.spark.internal.Logging
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.storage.StorageLevel


/**
 * @Author: chb
 * @Date: 2021/4/21 14:47
 * @E-Mail:
 * @DESC: 用户性别标签模型
 */
object GenderModel extends Logging {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = {
      // 1.a. 创建SparkConf 设置应用信息
      val sparkConf = new SparkConf()
        .setAppName(this.getClass.getSimpleName.stripSuffix("$"))
        .setMaster("local[4]")
        .set("spark.sql.shuffle.partitions", "4")
        // 由于从HBase表读写数据，设置序列化
        .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
        .registerKryoClasses(
          Array(classOf[ImmutableBytesWritable],
            classOf[Result], classOf[Put])
        )
      // 1.b. 建造者模式构建SparkSession对象
      val session = SparkSession.builder()
        .config(sparkConf)
        .getOrCreate()
      // 1.c. 返回会话实例对象
      session
    }
    import org.apache.spark.sql.functions._
    import spark.implicits._

    // TODO: 2. 从MySQL数据库读取标签数据（基础标签表：tbl_basic_tag），依据业 务标签ID读取
    val tagTable: String =
      """
        |(
        |SELECT `id`,
        | `name`,
        | `rule`,
        | `level`
        |FROM `profile_tags`.`tbl_basic_tag`
        |WHERE id = 315
        |UNION
        |SELECT `id`,
        | `name`,
        | `rule`,
        | `level`
        |FROM `profile_tags`.`tbl_basic_tag`
        |WHERE pid = 315
        |ORDER BY `level` ASC, `id` ASC
        |) AS basic_tag
        |""".stripMargin
    val basicTagDF: DataFrame = spark.read
      .format("jdbc")
      .option("driver", Constant.SQL_DRIVER)
      .option("url", Constant.SQL_URL)
      .option("dbtable", tagTable)
      .option("user", Constant.SQL_USER)
      .option("password", Constant.SQL_PSWD)
      .load()
    // 由于后续多次使用到标签数据，所以此处缓存。另外从MySQL表读取数据，使用子查询作为临时表读取。
    basicTagDF.persist(StorageLevel.MEMORY_AND_DISK)
    /*
    root
    |-- id: long (nullable = false)
    |-- name: string (nullable = true)
    |-- rule: string (nullable = true)
    |-- level: integer (nullable = true)
    */
    basicTagDF.printSchema()
    basicTagDF.show(20, truncate = false)


    // TODO: 3. 依据业务标签规则获取业务数据，比如到HBase数据库读取表的数据
    // 3.1. 4级标签规则rule
    val tagRule: String = basicTagDF
      .filter($"level" === 4)
      .head()
      .getAs[String]("rule")
    logInfo(s"==== 业务标签数据规则: {$tagRule} ====")
    // 3.2. 解析标签规则，先按照换行\n符分割，再按照等号=分割
    /*
    inType=hbase
    zkHosts=bigdata-cdh01.itcast.cn
    zkPort=2181
    hbaseTable=tbl_tag_users
    family=detail
    selectFieldNames=id,gender
    */
    val ruleMap: Map[String, String] = tagRule
      .split("\\n")
      .map { line =>
        val Array(attrName, attrValue) = line.trim.split("=")
        (attrName, attrValue)
      }
      .toMap
    logWarning(s"============ { ${ruleMap.mkString(", ")} } =========== ")
    // 3.3. 依据标签规则中inType类型获取数据
    var businessDF: DataFrame = null
    if ("hbase".equals(ruleMap("inType").toLowerCase())) {
      // 规则数据封装到HBaseMeta中
      val hbaseMeta: HBaseMeta = HBaseMeta.getHBaseMeta(ruleMap)
      // 依据条件到HBase中获取业务数据
    } else {
      // 如果未获取到数据，直接抛出异常
      new RuntimeException("业务标签未提供数据源信息，获取不到业务数据，无法计算标签")
    }
    businessDF.printSchema()
    businessDF.show(20, truncate = false)

    //    // TODO: 4. 业务数据和属性标签结合，构建标签：规则匹配型标签 -> rule match
    //    // 4.1 获取5级标签对应tagRule和tagName
    //    val attrTagRuleDF: DataFrame = basicTagDF
    //      .filter($"level" === 5)
    //      .select($"rule", $"name")
    //    // 4.2 DataFrame 关联，依据属性标签规则rule与业务数据字段gender
    //    val modelDF: DataFrame = businessDF
    //      .join(
    //        attrTagRuleDF, businessDF("gender") ===
    //          attrTagRuleDF("rule")
    //      )
    //      .select(
    //        $"id".as("userId"), //
    //        $"name".as("gender") //
    //      )
    //    /*
    //    root
    //    |-- userId: string (nullable = true)
    //    |-- gender: string (nullable = true)
    //    */
    //    //modelDF.printSchema()
    //    /*
    //    +------+------+
    //    |userId|gender|
    //    +------+------+
    //    |1 |女 |
    //    |10 |女 |
    //    |100 |女 |
    //    |101 |男 |
    //    */
    //    //modelDF.show(100, truncate = false)
    //    basicTagDF.unpersist()
    //
    //    // TODO: 5. 将标签数据存储到HBase表中：用户画像标签表 -> tbl_profile
    //    HBaseTools.write(
    //      modelDF, Constant.ZOOKEEPER_HOSTS, Constant.ZOOKEEPER_port, //
    //      "tbl_profile", "user", "userId"
    //    )*/

    // 应用结束，关闭资源
    spark.stop()

  }
}
