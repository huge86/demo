package com.pingpukj.tags

/**
 * @Author: chb
 * @Date: 2021/4/22 8:45
 * @E-Mail:
 * @DESC:
 */
object Constant {
  val host = "192.168.80.10"
  val HIVE_METASTORE = s"thrift://chb2:9083"
  val HIVE_WAREHOUSE = s"hdfs://chb3:8020/user/hive/warehouse"

  val ZOOKEEPER_HOSTS = host
  val ZOOKEEPER_port = "2181"

  // 数据库
  val SQL_DRIVER = "com.mysql.jdbc.Driver"
  val SQL_URL = s"jdbc:mysql://chb1:3306/?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC"
  val SQL_USER = "root"
  val SQL_PSWD = "123456"

  val TAG_TABLE_HBASE = "tbl_profile"

}
