package com.pingpukj.tags.etl

import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.filter.{BinaryComparator, ValueFilter}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{Cell, CompareOperator, HBaseConfiguration, TableName}

import java.util

/**
 * @Date: 2021/5/10 17:28
 * @DESC:
 */
object TestHbase {
  def main(args: Array[String]): Unit = {
    put()
   // get()
  }


  def get(): Unit = {
    println("test get...")
    val hbaseConf = HBaseConfiguration.create()
    val hbaseConn = ConnectionFactory.createConnection(hbaseConf)


    val table = hbaseConn.getTable(TableName.valueOf("test1"))
    val scan = new Scan
    val hotelId = "h005"

    scan.withStartRow(Bytes.toBytes(hotelId + "_"))
    scan.withStopRow(Bytes.toBytes(hotelId + "_~"))
    scan.addColumn(Bytes.toBytes("property"), Bytes.toBytes("amount_last12"))

    var b = "000000" + BigDecimal(3000).formatted("%6.4f")
    b = b.substring(b.length - 11, b.length)
    //    scan.setFilter(new SingleColumnValueFilter(Bytes.toBytes("property"), Bytes.toBytes("amount_last12"),
    //      CompareOperator.GREATER, new BinaryComparator(Bytes.toBytes(limit))))
    scan.setFilter(new ValueFilter(CompareOperator.GREATER, new BinaryComparator(Bytes.toBytes(b))))


    val scanner: ResultScanner = table.getScanner(scan)

    val iterator: util.Iterator[Result] = scanner.iterator
    while (iterator.hasNext) {
      val rs: Result = iterator.next
      val rowkey: String = Bytes.toString(rs.getRow)
      val cell: Cell = rs.getColumnLatestCell(Bytes.toBytes("property"), Bytes.toBytes("amount_last12"))

      println(Bytes.toString(cell.getValueArray, cell.getValueOffset, cell.getValueLength))


      println(rowkey)
    }


    table.close()
  }

  def put(): Unit = {
    val hbaseConf = HBaseConfiguration.create()
    val hbaseConn = ConnectionFactory.createConnection(hbaseConf)


    val table = hbaseConn.getTable(TableName.valueOf("hotel_profile"))

    val put = new Put(Bytes.toBytes("h006_001"))
    var a = "000000" + BigDecimal(2000).formatted("%6.4f")
    a = a.substring(a.length - 11, a.length)
    put.addColumn(Bytes.toBytes("property"), Bytes.toBytes("amount_last12"), Bytes.toBytes(a))

    val put2 = new Put(Bytes.toBytes("h006_002"))
    var b = "000000" + BigDecimal(5000).formatted("%6.4f")
    b = b.substring(b.length - 11, b.length)

    put2.addColumn(Bytes.toBytes("property"), Bytes.toBytes("amount_last12"), Bytes.toBytes(b))

    table.put(put)
    table.put(put2)

    table.close()
  }
}
