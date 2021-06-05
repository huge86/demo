package com.pingpukj.tags.etl.bulkload2


import com.pingpukj.tags.etl.bulkload2.base.ExportToHbaseTemplate
import org.apache.hadoop.hbase.KeyValue
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame

import scala.collection.immutable.TreeMap

/**
 * @Author: chb
 * @Date: 2021/4/28 13:13
 * @E-Mail:
 * @DESC: 测试多个列簇， 多列
 */
class MultiCFTest extends ExportToHbaseTemplate("hotel_checkin", "MultiCFTest") {

  /**
   * 加载数据
   *
   * @return
   */
  override def getDataset(): DataFrame = {
    val srcDf = spark.sql(
      """
        | select shotelid, dbizday, scusaccid, dmodifydate, nvl(cprice, 0) cprice from chb_test.om_rec_main_int_f_v2_tmp
        | where dmodifydate > '2021-04-26 00:00:00' AND shotelid != 'shotelid'
        |""".stripMargin)
    // srcDf.show()

    srcDf
  }

  /**
   * 将dataset处理成Hbase的数据格式
   * 注：
   * 默认API只能处理一个列族一个列的情况
   * 此处扩展了该功能：
   * 用var kvlist: Seq[KeyValue] = List()
   * 和rdd.flatMapValues(_.iterator) 方式自适应列名
   * 处理后的结果为：一个列族多个列
   *
   * @return
   */
  override def dataSet2KeyValue(SourceDataFrame: DataFrame): RDD[(ImmutableBytesWritable, KeyValue)] = {
    import SourceDataFrame.sparkSession.implicits._

    val colToCFMap = TreeMap("customer" -> "scusaccid", "amount" -> "cprice")

    // shotelid, dbizday, scusaccid, dmodifydate, cprice
    val result1: RDD[(ImmutableBytesWritable, Seq[KeyValue])] = SourceDataFrame
      .repartition(200, $"shotelid", ($"dmodifydate").substr(0, 10)) //如果数据量大，可以根据key进行分区操作
      .rdd
      .map(row => {
        val rowKey = row.getAs[String]("shotelid") + "_" + row.getAs[String]("dmodifydate").substring(0, 10)
        (rowKey, List(row))
      }).reduceByKey(_ ++ _)
      .map(rows => {
        val immutableRowKey: ImmutableBytesWritable = new ImmutableBytesWritable(Bytes.toBytes(rows._1))
        var kvlist: Seq[KeyValue] = List() //存储多个列
        var kv: KeyValue = null


        for (i <- 0 until rows._2.size) {
          for (cf <- colToCFMap.keys) {
            val colName = colToCFMap.get(cf).get
            val row = rows._2(i)
            val colValue = row.getAs(colName).toString

            val isIndex = true
            val qualifier = if (isIndex) {
              Bytes.toBytes(i + "")
            } else {
              Bytes.toBytes(colName)
            }

            //封装KeyValue
            kv = new KeyValue(Bytes.toBytes(rows._1), Bytes.toBytes(cf), qualifier, Bytes.toBytes(colValue))

            //将新的kv加在kvlist后面（不能反 需要整体有序）
            kvlist = kvlist :+ kv
          }
        }

        (immutableRowKey, kvlist)
      })

    val hfileRDD: RDD[(ImmutableBytesWritable, KeyValue)] = result1
      .flatMapValues(_.iterator)

    hfileRDD

  }
}


object MultiCFTest {
  def main(args: Array[String]): Unit = {
    val test = new MultiCFTest();
    test.runWork(args)
  }
}
