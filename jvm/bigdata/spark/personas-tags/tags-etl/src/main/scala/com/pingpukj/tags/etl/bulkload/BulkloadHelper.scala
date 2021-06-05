package com.pingpukj.tags.etl.bulkload
import com.pingpukj.tags.etl.bulkload.HFilePartitionerHelper.HFilePartitioner

import java.time.LocalDate
import java.util.UUID
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.hbase.{HBaseConfiguration, KeyValue, TableName}
import org.apache.hadoop.hbase.client.{ConnectionFactory, HTable, Put}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2
import org.apache.hadoop.hbase.tool.LoadIncrementalHFiles
import org.apache.hadoop.hbase.util.Bytes
import org.apache.log4j.Logger
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
/**
 * Author: HuZunJie
 * Date: 2021-06-05 20:10
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc:
 */


/**
 * https://www.cnblogs.com/keepthinking/p/10364869.html
 */
object BulkloadHelper {
  private val logger = Logger.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    // 1. 构建SparkContext实例对象
    val sc: SparkContext = {
      // a. 创建SparkConf，设置应用配置信息
      val sparkConf = new SparkConf()
        //  .setMaster("local[2]")
        .setAppName(this.getClass.getSimpleName.stripSuffix("$"))
        .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      // b. 传递SparkContext创建对象
      SparkContext.getOrCreate(sparkConf)
    }
    val rdd = sc.parallelize(Seq((4, "lisi", "44"))).map(t => {
      val put = new Put(Bytes.toBytes(t._1))
      put.addColumn(Bytes.toBytes("detail"), Bytes.toBytes("name"), Bytes.toBytes(t._2))
      put.addColumn(Bytes.toBytes("detail"), Bytes.toBytes("age"), Bytes.toBytes(t._3))
      put
    })

    val hbaseConf = HBaseConfiguration.create()
    hbaseConf.set("hbase.mapreduce.hfileoutputformat.table.name", "person")
    bulkloadWrite(rdd, hbaseConf, TableName.valueOf("person"))

  }

  def bulkloadWrite(rdd: RDD[Put], hbaseConfig: Configuration, thisTableName: TableName): Unit = {
    val hbaseConnection = ConnectionFactory.createConnection(hbaseConfig)
    val regionLocator = hbaseConnection.getRegionLocator(thisTableName)
    val myPartitioner = HFilePartitioner.apply(hbaseConfig, regionLocator.getStartKeys, 1)

    logger.info(s"regionLocator.getStartKeys.length = ${regionLocator.getStartKeys.length}")
    regionLocator.getStartKeys.foreach(keys => logger.info("regionLocator.getStartKeys: " + new String(keys)))

    val hFilePath = getHFilePath()
    logger.info(s"bulkload, begin to write to hdfs path: $hFilePath")

    /**
     * HFile sort function -> KeyValue.KVComparator
     * CellComparator
     */
    rdd.flatMap(put => putToKeyValueList(put))
      .map(c => (c, 1))
      .repartitionAndSortWithinPartitions(myPartitioner) // repartition so each hfile can match the hbase region
      .map(tuple => (new ImmutableBytesWritable(tuple._1.row), tuple._1.getKeyValue()))
      .saveAsNewAPIHadoopFile(
        hFilePath,
        classOf[ImmutableBytesWritable],
        classOf[KeyValue],
        classOf[HFileOutputFormat2],
        hbaseConfig)

    //  Bulk load Hfiles to Hbase
    logger.info("bulkload, begin to load to hbase")
    val bulkLoader = new LoadIncrementalHFiles(hbaseConfig)


    //  bulkLoader.doBulkLoad(new Path(hFilePath), new HTable(hbaseConfig, thisTableName))
    bulkLoader.doBulkLoad(new Path(hFilePath), hbaseConnection.getAdmin, hbaseConnection.getTable(thisTableName), regionLocator)

    logger.info("bulkload, delete hdfs path")
    val hadoopConf = new Configuration()
    val fileSystem = FileSystem.get(hadoopConf)
    fileSystem.delete(new Path(hFilePath), true)
    hbaseConnection.close()
    fileSystem.close()
    logger.info("bulkload, done")
  }

  def getHFilePath(): String = "/user/hadoop/hbase/bulkload/hfile/" + LocalDate.now().toString + "-" + UUID.randomUUID().toString

  /**
   * select one keyvalue from put
   *
   * @param put
   */
  def putToKeyValueList(put: Put): Seq[MyKeyValue] = {

    import scala.collection.JavaConverters._

    put.getFamilyCellMap.asScala
      .flatMap(_._2.asScala) // list cells
      .map(cell => new MyKeyValue(put.getRow, cell.getFamilyArray, cell.getQualifierArray, cell.getTimestamp, cell.getValueArray))
      .toSeq
  }
}