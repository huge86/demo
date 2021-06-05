package com.pingpukj.tags.etl.bulkload2


import com.pingpukj.tags.etl.bulkload2.base.ExportToHbaseTemplate
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.KeyValue
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * 单列簇，多列
 */
class OneCFTest(outPutTable: String, APPName: String) extends ExportToHbaseTemplate(outPutTable: String, APPName: String) {


  var cf: String = "detail" //列族

  var defKey: String = "id" //默认key
  var partition: String = "20190918"

//  /**
//   * 获取源数据表
//   */
//  override def getDataset(): DataFrame = {
//    import spark.implicits._
//    spark.sparkContext.parallelize(Seq(Person(3, null, 33))).toDF()
//  }


  /**
   * 加载数据
   *
   * @return
   */
  override def getDataset(): DataFrame = {
    val selectFields = "shotelid, dbizday, scusaccid, dmodifydate, nvl(cprice, 0) cprice"  // 所有字段就是 *
    val hiveTalbe = "chb_test.om_rec_main_int_f_v2_tmp"
    val incrTime = LocalDateTime.now().plusDays(-2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"))
    val partitions = "'2021-04', '2021-03', '2021-02'"

    val srcDf = spark.sql(
      s"""
        | select ${selectFields} from ${hiveTalbe}
        | where dmodifydate > '${incrTime}' AND shotelid != 'shotelid'
        | AND createym in (${partitions})
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

    //key：全局变量不能在 map  内部使用  所以创建一个局部变量
    //注：如果不做会出现奇怪的异常比如类初始化失败，spark为初始化等，目前还没发现具体原因，后续去跟踪
    val key = defKey
    //列族
    val clounmFamily: String = cf
    //获取列名 第一个为key
    val columnsName: Array[String] = SourceDataFrame.columns.sorted

    val result1: RDD[(ImmutableBytesWritable, Seq[KeyValue])] = SourceDataFrame
      .repartition(200, $"$key") //如果数据量大，可以根据key进行分区操作
      .rdd
      .map(row => {
        var kvlist: Seq[KeyValue] = List() //存储多个列
        var kv: KeyValue = null
        val cf: Array[Byte] = clounmFamily.getBytes //列族

        val rowKey = Bytes.toBytes(row.getAs[Int](key) + "")
        val immutableRowKey: ImmutableBytesWritable = new ImmutableBytesWritable(rowKey)

        for (i <- 0 to (columnsName.length - 1)) {
          //将rdd转换成HFile需要的格式,
          //我们上面定义了Hfile的key是ImmutableBytesWritable,
          //那么我们定义的RDD也是要以ImmutableBytesWritable的实例为key
          var value: Array[Byte] = null
          try {
            //数据是字符串的都映射成String
            value = Bytes.toBytes(row.getAs[String](columnsName(i)))
          } catch {
            case e: ClassCastException =>
              //出现数据类型转换异常则说明是数字,都映射成BigInt
              value = Bytes.toBytes(row.getAs[BigInt](columnsName(i)) + "")
            case e: Exception =>
              e.printStackTrace()
          }
          //封装KeyValue
          kv = new KeyValue(rowKey, cf, Bytes.toBytes(columnsName(i)), value)
          //将新的kv加在kvlist后面（不能反 需要整体有序）
          kvlist = kvlist :+ kv
        }
        (immutableRowKey, kvlist)
      })

    val hfileRDD: RDD[(ImmutableBytesWritable, KeyValue)] = result1
      .flatMapValues(_.iterator)

    hfileRDD
  }
}

object OneCFTest {
  def main(args: Array[String]): Unit = {
    val test = new OneCFTest("person", "OneCFTest")
    test.runWork(args)
  }
}
