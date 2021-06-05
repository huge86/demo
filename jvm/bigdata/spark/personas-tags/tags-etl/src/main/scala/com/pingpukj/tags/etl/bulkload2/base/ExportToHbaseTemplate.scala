package com.pingpukj.tags.etl.bulkload2.base


import com.pingpukj.tags.etl.bulkload2.util.ReadProperties
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.fs.permission.{FsAction, FsPermission}
import org.apache.hadoop.hbase.{HBaseConfiguration, KeyValue, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, RegionLocator, Table}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2
import org.apache.hadoop.hbase.tool.LoadIncrementalHFiles
import org.apache.hadoop.mapreduce.Job
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.storage.StorageLevel
import org.slf4j.{Logger, LoggerFactory}

/**
 * @author chb
 *         TODO: 使用模板方法模式创建任务执行流程，保证所有任务的流程统一，所有非流处理任务需要实现此接口
 */
abstract class ExportToHbaseTemplate(outPutTable: String, APPName: String) {
  val logger: Logger = LoggerFactory.getLogger(getClass.getSimpleName)
  //任务状态
  val PERSIST_LEVEL: StorageLevel = StorageLevel.MEMORY_AND_DISK_SER

  var conf: Configuration = _

  var savePath: String = s"/tmp/hbase/$outPutTable" //临时HFile保存路径


  val proPath = "/hdfs.properties" //配置文件路径
  val proInstance = ReadProperties.getInstance(proPath).getProInstance


  val spark: SparkSession = SparkSession
    .builder()
    //    .master("local")
    .appName(APPName)
    .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    .enableHiveSupport()
    .getOrCreate()


  /**
   * 任务模板: 数据处理流
   *
   * @param args
   */
  def runWork(args: Array[String]): Unit = {
    try {
      init(args) // 初始化信息

      // 加载数据
      val SourceDataFrame: DataFrame = getDataset()

      val hfileRDD: RDD[(ImmutableBytesWritable, KeyValue)] = dataSet2KeyValue(SourceDataFrame)

      saveHfile(hfileRDD)

      loadHFileToHbase()
    } catch {
      case e: Exception =>
        e.printStackTrace
    } finally {
      spark.sparkContext.stop()
    }
  }

  /**
   * 初始化信息
   *
   * @param args
   */
  def init(args: Array[String]): Unit = {
    conf = HBaseConfiguration.create() //Hbase配置信息
    conf.set("hbase.zookeeper.quorum", proInstance.getProperty("zk")) //Hbase zk信息
    conf.set("hbase.mapreduce.hfileoutputformat.table.name", outPutTable) //Hbase 输出表
    conf.set("hbase.unsafe.stream.capability.enforce", "false") //hbase  根目录设定  （有时候会报错，具体看错误处理部分）
    conf.set("zookeeper.znode.parent", "/hbase-unsecure")
    conf.set("hbase.mapreduce.bulkload.max.hfiles.perRegion.perFamily", "400")
  }

  /**
   * 加载数据
   *
   * @return
   */
  def getDataset(): DataFrame

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
  def dataSet2KeyValue(SourceDataFrame: DataFrame): RDD[(ImmutableBytesWritable, KeyValue)]
//  def getHFileRDD(SourceDataFrame: DataFrame): RDD[(ImmutableBytesWritable, KeyValue)] = {
//    import SourceDataFrame.sparkSession.implicits._
//
//    //key：全局变量不能在 map  内部使用  所以创建一个局部变量
//    //注：如果不做会出现奇怪的异常比如类初始化失败，spark为初始化等，目前还没发现具体原因，后续去跟踪
//    val key = defKey
//    //列族
//    val clounmFamily: String = cf
//    //获取列名 第一个为key
//    val columnsName: Array[String] = SourceDataFrame.columns.sorted
//
//    val result1: RDD[(ImmutableBytesWritable, Seq[KeyValue])] = SourceDataFrame
//      .repartition(200, $"$key") //如果数据量大，可以根据key进行分区操作
//      .rdd
//      .map(row => {
//        var kvlist: Seq[KeyValue] = List() //存储多个列
//        var kv: KeyValue = null
//        val cf: Array[Byte] = clounmFamily.getBytes //列族
//        val rowKey = Bytes.toBytes(row.getAs[Int](key) + "")
//        val immutableRowKey: ImmutableBytesWritable = new ImmutableBytesWritable(rowKey)
//        for (i <- 0 to (columnsName.length - 1)) {
//          //将rdd转换成HFile需要的格式,
//          //我们上面定义了Hfile的key是ImmutableBytesWritable,
//          //那么我们定义的RDD也是要以ImmutableBytesWritable的实例为key
//          var value: Array[Byte] = null
//          try {
//            //数据是字符串的都映射成String
//            value = Bytes.toBytes(row.getAs[String](columnsName(i)))
//          } catch {
//            case e: ClassCastException =>
//              //出现数据类型转换异常则说明是数字,都映射成BigInt
//              value = Bytes.toBytes(row.getAs[BigInt](columnsName(i)) + "")
//            case e: Exception =>
//              e.printStackTrace()
//          }
//          //封装KeyValue
//          kv = new KeyValue(rowKey, cf, Bytes.toBytes(columnsName(i)), value)
//          //将新的kv加在kvlist后面（不能反 需要整体有序）
//          kvlist = kvlist :+ kv
//        }
//        (immutableRowKey, kvlist)
//      })
//
//    val hfileRDD: RDD[(ImmutableBytesWritable, KeyValue)] = result1
//      .flatMapValues(_.iterator)
//
//    hfileRDD
//  }



  /**
   * HFile 导入HBase
   */
  def loadHFileToHbase() = {
    //开始即那个HFile导入到Hbase,此处都是hbase的api操作
    val load: LoadIncrementalHFiles = new LoadIncrementalHFiles(conf)

    //创建hbase的链接,利用默认的配置文件,实际上读取的hbase的master地址
    val conn: Connection = ConnectionFactory.createConnection(conf)

    //根据表名获取表
    val table: Table = conn.getTable(TableName.valueOf(outPutTable))

    //获取hbase表的region分布
    val regionLocator: RegionLocator = conn.getRegionLocator(TableName.valueOf(outPutTable))

    //创建一个hadoop的mapreduce的job
    val job: Job = Job.getInstance(conf)

    //设置job名称
    job.setJobName(s"$outPutTable LoadIncrementalHFiles")

    //此处最重要,需要设置文件输出的key,因为我们要生成HFil,所以outkey要用ImmutableBytesWritable
    job.setMapOutputKeyClass(classOf[ImmutableBytesWritable])

    //输出文件的内容KeyValue
    job.setMapOutputValueClass(classOf[KeyValue])

    //配置HFileOutputFormat2的信息
    HFileOutputFormat2.configureIncrementalLoad(job, table, regionLocator)

    //开始导入
    load.doBulkLoad(new Path(savePath), conn.getAdmin, table, regionLocator)
  }


  /**
   * 删除hdfs下的文件
   *
   * @param url 需要删除的路径
   */
  def delete_hdfspath(url: String) {
    val hdfs: FileSystem = FileSystem.get(new Configuration)
    val path: Path = new Path(url)
    if (hdfs.exists(path)) {
      val filePermission = new FsPermission(FsAction.ALL, FsAction.ALL, FsAction.READ)
      hdfs.delete(path, true)
    }
  }


  /**
   * 保存生成的HFile文件
   * 注：bulk load  生成的HFile文件需要落地
   * 然后再通过LoadIncrementalHFiles类load进Hbase
   * 此处关于  sortBy 操作详解：
   * 0. Hbase查询是根据rowkey进行查询的，并且rowkey是有序，
   * 某种程度上来说rowkey就是一个索引，这是Hbase查询高效的一个原因，
   * 这就要求我们在插入数据的时候，要插在rowkey该在的位置。
   * 1. Put方式插入数据，会有WAL，同时在插入Hbase的时候会根据RowKey的值选择合适的位置，此方式本身就可以保证RowKey有序
   * 2. bulk load 方式没有WAL，它更像是hive通过load方式直接将底层文件HFile移动到制定的Hbase路径下，所以，在不东HFile的情况下，要保证本身有序才行
   * 之前写的时候只要rowkey有序即可，但是2.0.2版本的时候发现clounm也要有序，所以会有sortBy(x => (x._1, x._2.getKeyString), true)
   *
   * @param hfileRDD
   */
  def saveHfile(hfileRDD: RDD[(ImmutableBytesWritable, KeyValue)]) = {
    //删除可能存在的文件，否则回报文件已存在异常
    delete_hdfspath(savePath)

    //生成的HFile保存到指定目录
    hfileRDD
      .sortBy(x => (x._1, x._2.getKeyString), true) //要保持 整体有序
      .saveAsNewAPIHadoopFile(savePath,
        classOf[ImmutableBytesWritable],
        classOf[KeyValue],
        classOf[HFileOutputFormat2],
        conf)
  }


}
