package com.pingpukj.tags.etl.bulkload2.util

import java.io.FileInputStream
import java.util.Properties

/**
 * 读取.properties配置文件
 *
 * @param path
 */
class ReadProperties(path: String) {
  /**
   * 读取、加载指定路径配置文件
   *
   * @return Properties 实例
   */
  def getProInstance(): Properties = {
    val pro = new Properties()
    // new FileInputStream(path)
    val stream = this.getClass.getResourceAsStream(path)
    pro.load(stream)
    pro
  }
}

/**
 * 伴生对象
 */
object ReadProperties {
  def getInstance(path: String): ReadProperties = {
    new ReadProperties(path)
  }
}
