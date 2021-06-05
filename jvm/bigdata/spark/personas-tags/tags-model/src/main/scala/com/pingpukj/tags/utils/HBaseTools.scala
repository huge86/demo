package com.pingpukj.tags.utils

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client.{Put, Result, Scan}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.{TableInputFormat, TableOutputFormat}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
 * @Author: chb
 * @Date: 2021/4/21 15:32
 * @E-Mail:
 * @DESC: 从HBase表读写数据Client封装类，方便使用
 */
object HBaseTools {
  /**
   * 依据指定表名称、列簇及列名称从HBase表中读取数据
   *
   * @param spark  SparkSession 实例对象
   * @param zks    Zookerper 集群地址
   * @param port   Zookeeper端口号
   * @param table  HBase表的名称
   * @param family 列簇名称
   * @param fields 列名称
   * @return
   */
  def read(spark: SparkSession, zks: String, port: String, table: String, family: String, fields: Seq[String]): DataFrame = {

    // 1. 设置HBase中Zookeeper集群信息
    val conf: Configuration = new Configuration()
    conf.set("hbase.zookeeper.quorum", zks)
    conf.set("hbase.zookeeper.property.clientPort", port.toString)
    // 2. 设置读HBase表的名称
    conf.set(TableInputFormat.INPUT_TABLE, table)
    // 3. 设置读取列簇和列名称
    val scan: Scan = new Scan()
    // 3.1. 设置列簇
    val familyBytes = Bytes.toBytes(family)
    scan.addFamily(familyBytes)
    // 3.2. 设置列名称
    fields.foreach { field =>
      scan.addColumn(familyBytes, Bytes.toBytes(field))
    }
//    // 3.3. 设置scan过滤
//    conf.set(
//      TableInputFormat.SCAN,
//      Base64.encodeBytes(ProtobufUtil.toScan(scan).toByteArray)
//    )
    // 4. 调用底层API，读取HBase表的数据
    val datasRDD: RDD[(ImmutableBytesWritable, Result)] =
      spark.sparkContext
        .newAPIHadoopRDD(
          conf,
          classOf[TableInputFormat],
          classOf[ImmutableBytesWritable],
          classOf[Result]
        )
    // =====================================================================
    // 将读取HBase数据RDD提取封装为DataFrame
    // =====================================================================
    // 5. 解析转换为DataFrame
    val rowsRDD: RDD[Row] = datasRDD.map { case (_, result) =>
      // 5.1. 列的值
      val values: Seq[String] = fields.map { field =>
        Bytes.toString(result.getValue(familyBytes, Bytes.toBytes(field)))
      }
      // 5.2. 生成Row对象
      Row.fromSeq(values)
    }
    // 6. 定义Schema信息
    val rowSchema: StructType = StructType(
      fields.map { field => StructField(field, StringType, nullable = true) }
    )
    // 7. 转换为DataFrame
    spark.createDataFrame(rowsRDD, rowSchema)
  }


  /**
   * 将DataFrame数据保存到HBase表中
   *
   * @param dataframe    数据集DataFrame
   * @param zks          Zk地址
   * @param port         端口号
   * @param table        表的名称
   * @param family       列簇名称
   * @param rowKeyColumn RowKey字段名称
   */
  def write(dataframe: DataFrame, zks: String, port: String, table: String, family: String, rowKeyColumn: String): Unit = {
    // 1. 设置HBase中Zookeeper集群信息
    val conf: Configuration = new Configuration()
    conf.set("hbase.zookeeper.quorum", zks)
    conf.set("hbase.zookeeper.property.clientPort", port)
    // 2. 设置读HBase表的名称
    conf.set(TableOutputFormat.OUTPUT_TABLE, table)
    // 3. 数据转换
    val columns: Array[String] = dataframe.columns
    val putsRDD: RDD[(ImmutableBytesWritable, Put)] =
      dataframe.rdd.map { row =>
        // 获取RowKey
        val rowKey: String = row.getAs[String](rowKeyColumn)
        // 构建Put对象
        val put = new Put(Bytes.toBytes(rowKey))
        // 将每列数据加入Put对象中
        val familyBytes = Bytes.toBytes(family)
        columns.foreach { column =>
          put.addColumn(
            familyBytes, //
            Bytes.toBytes(column), //
            Bytes.toBytes(row.getAs[String](column)) //
          )
        }
        // 返回二元组
        (new ImmutableBytesWritable(put.getRow), put)
      }
    // 4. 保存数据到表
    putsRDD.saveAsNewAPIHadoopFile(
      s"/apps/hbase/$table-" + System.currentTimeMillis(),
      classOf[ImmutableBytesWritable], //
      classOf[Put], //
      classOf[TableOutputFormat[ImmutableBytesWritable]], //
      conf //
    )

  }
}
