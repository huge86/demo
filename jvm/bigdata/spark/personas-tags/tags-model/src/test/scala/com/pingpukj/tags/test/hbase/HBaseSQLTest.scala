package com.pingpukj.tags.test.hbase

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

/**
 * 测试自定义外部数据源实现从HBase表读写数据接口
 */
object HBaseSQLTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(this.getClass.getSimpleName.stripSuffix("$"))
      .master("local[4]")
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .getOrCreate()

    import spark.implicits._
    testRead(spark)


    spark.stop()
  }

  def testWrite(usersDF: DataFrame): Unit = {
    // 保存数据
    usersDF.write
      .mode(SaveMode.Overwrite)
      .format("com.chb.spark.hbase")
//      .option("zkHosts", "chb")
//      .option("zkPort", "2181")
      .option("hbaseTable", "tbl_users")
      .option("family", "info")
      .option("rowKeyColumn", "id")
      .save()
  }

  def testRead(spark: SparkSession): Unit = {
    // 读取数据
    val usersDF: DataFrame = spark.read
      .format("com.chb.spark.hbase")
      .option("zkHosts", "dev-hdp-4.huazhu.com,dev-hdp-6.huazhu.com,dev-hdp-5.huazhu.com")
      .option("zkPort", "2181")
      .option("hbaseTable", "chb_test_tags")
      .option("family", "user")
      .option("selectFields", "id,gender")
      .load()
    usersDF.printSchema()
    usersDF.cache()
    usersDF.collect().foreach(println(_))
  }
}
