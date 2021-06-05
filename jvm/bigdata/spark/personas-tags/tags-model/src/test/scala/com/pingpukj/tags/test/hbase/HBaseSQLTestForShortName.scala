package com.pingpukj.tags.test.hbase

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

/**
 * 测试自定义外部数据源实现从HBase表读写数据接口
 */
object HBaseSQLTestForShortName {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(this.getClass.getSimpleName.stripSuffix("$"))
      .master("local[4]")
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .getOrCreate()

    // 读取数据
    val usersDF: DataFrame = spark.read
      .format("hbase")
      .option("zkHosts", "ch1")
      .option("zkPort", "2181")
      .option("hbaseTable", "tbl_tag_users")
      .option("family", "detail")
      .option("selectFields", "id,gender")
      .load()
    usersDF.printSchema()
    usersDF.cache()
    usersDF.show(10, truncate = false)


    // 保存数据
    usersDF.write
      .mode(SaveMode.Overwrite)
      .format("hbase")
      .option("zkHosts", "chb")
      .option("zkPort", "2181")
      .option("hbaseTable", "tbl_users")
      .option("family", "info")
      .option("rowKeyColumn", "id")
      .save()
    spark.stop()
  }
}
