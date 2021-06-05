package com.pingpukj.tags.up

import pureconfig.ConfigSource
import com.typesafe.config.ConfigFactory
object ConfigHolder {
  import pureconfig._
  //  import pureconfig.generic.auto._
  import pureconfig.generic.auto._
  private val configTool = ConfigFactory.load("up")
  val config: Config = ConfigSource.fromConfig(configTool).load[Config].right.get
  val model: Model = config.model
  val hadoop: Hadoop = config.hadoop
  val oozie: Oozie = config.oozie
  val mysql: MySQL = config.mysql

}
