package com.pingpukj.tags.etl.bulkload
import org.apache.hadoop.hbase.KeyValue
/**
 * Author: HuZunJie
 * Date: 2021-06-05 20:12
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc:
 */



class MyKeyValue(var row: Array[Byte], var family: Array[Byte], var qualifier: Array[Byte], var timestamp: Long, var value: Array[Byte])
  extends Serializable with Ordered[MyKeyValue] {

  import java.io.IOException
  import java.io.ObjectInputStream
  import java.io.ObjectOutputStream

  var keyValue: KeyValue = _

  def getKeyValue(): KeyValue = {
    if (keyValue == null) {
      keyValue = new KeyValue(row, family, qualifier, timestamp, value)
    }
    keyValue
  }

  @throws[IOException]
  private def writeObject(out: ObjectOutputStream) {
    keyValue = null
    out.defaultWriteObject()
    out.writeObject(this)
  }

  @throws[IOException]
  @throws[ClassNotFoundException]
  private def readObject(in: ObjectInputStream) {
    in.defaultReadObject()
    val newKeyValue = in.readObject().asInstanceOf[MyKeyValue]
    this.row = newKeyValue.row
    this.family = newKeyValue.family
    this.qualifier = newKeyValue.qualifier
    this.timestamp = newKeyValue.timestamp
    this.value = newKeyValue.value
    getKeyValue()
  }

  class MyComparator extends KeyValue.KVComparator with Serializable {}
  val comparator = new MyComparator()

  override def compare(that: MyKeyValue): Int = {
    comparator.compare(this.getKeyValue(), that.getKeyValue())
  }

  override def toString: String = {
    getKeyValue().toString
  }
}