package com.pingpukj.tags.test.hbase


import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, ResultScanner, Scan, Table}
import org.apache.hadoop.hbase.filter.{CompareFilter, SingleColumnValueFilter}
import org.apache.hadoop.hbase.util.Bytes

/**
 * @Author: chb
 * @Date: 2021/4/26 7:53
 * @E-Mail:
 * @DESC: 从HBase表中读取数据，设置过滤条件：modified < '2019-09-01'
 */
object HBaseFilterTest {
  def main(args: Array[String]): Unit = {
    // 1. 设置HBase中Zookeeper集群信息
    val conf: Configuration = new Configuration()
//    conf.set("hbase.zookeeper.quorum", Constant.ZOOKEEPER_HOSTS)
//    conf.set("hbase.zookeeper.property.clientPort", Constant.ZOOKEEPER_HOSTS)

    // 2. 获取Connection连接对象
    val conn: Connection = ConnectionFactory.createConnection(conf)
    // 3. 获取Table句柄
    val table: Table = conn.getTable(TableName.valueOf("tbl_tag_orders"))

    // 4. 创建扫描器，设置过滤条件
    val scan: Scan = new Scan()
    // 过滤条件
    // SELECT COUNT(1) AS total FROM tbl_tag_orders WHERE modified < '2019 - 09 - 01';
    val filter: SingleColumnValueFilter = new SingleColumnValueFilter(
      Bytes.toBytes("detail"), Bytes.toBytes("modified"),
      CompareFilter.CompareOp.LESS, Bytes.toBytes("2019-09-01")
    )
    scan.setFilter(filter)
    // 设置获取列名称
    scan.addColumn(Bytes.toBytes("detail"), Bytes.toBytes("memberid"))
    scan.addColumn(Bytes.toBytes("detail"), Bytes.toBytes("paymentcode"))
    // 必须添加过滤条件的列
    scan.addColumn(Bytes.toBytes("detail"), Bytes.toBytes("modified"))
    // 5. 查询扫描数据
    val resultScanner: ResultScanner = table.getScanner(scan)
    import scala.collection.JavaConverters._
    println(s"count = ${resultScanner.iterator().asScala.size}")
    // 6. 关闭资源
    resultScanner.close()
    table.close()
    conn.close()
  }
}
