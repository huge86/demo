package com.pingpukj.tags.test.spark

import java.util
import java.util.Map
import com.typesafe.config.{Config, ConfigFactory, ConfigValue}

/**
 * @Author: chb
 * @Date: 2021/4/23 9:19
 * @E-Mail:
 * @DESC: 测试如何获取指定属性文件中属性值：
 */
object SparkConfigTest {
  def main(args: Array[String]): Unit = {
    // a、使用ConfigFactory加载spark.conf
    val config = ConfigFactory.load("spark.properties")
    // 获取加载配置信息
    val entrySet: util.Set[Map.Entry[String, ConfigValue]] = config.entrySet()
    // 遍历
    import scala.collection.JavaConverters._
    for (entry <- entrySet.asScala) {
      // 获取属性来源的文件名称
      val resource = entry.getValue.origin().resource()
      if ("spark.properties".equals(resource)) {
        println(entry.getKey + ": " + entry.getValue.unwrapped().toString)
      }
    }
  }
}
