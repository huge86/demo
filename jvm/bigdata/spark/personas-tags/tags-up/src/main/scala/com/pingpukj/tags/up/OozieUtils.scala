package com.pingpukj.tags.up

import org.apache.commons.lang3.StringUtils
import org.apache.oozie.client.{AuthOozieClient, OozieClient}
import java.util.Properties

object OozieUtils {
  val classLoader: ClassLoader = getClass.getClassLoader

  def genProperties(param: OozieParam): Properties = {
    val properties = new Properties()

    val params = ConfigHolder.oozie.params
    for (entry <- params) {
      properties.setProperty(entry._1, entry._2)
    }

    val appPath = ConfigHolder.hadoop.nameNode + genAppPath(param.modelId)
    properties.setProperty("appPath", appPath)

    properties.setProperty("mainClass", param.mainClass)
    properties.setProperty("jarPath", param.jarPath) // 要处理

    if (StringUtils.isNotBlank(param.sparkOptions)) properties.setProperty("sparkOptions", param.sparkOptions)
    properties.setProperty("start", param.start)
    properties.setProperty("end", param.end)
    properties.setProperty(OozieClient.COORDINATOR_APP_PATH, appPath)

    properties
  }

  def uploadConfig(modelId: Long): Unit = {
    val workflowFile = classLoader.getResource("oozie/workflow.xml").getPath
    val coordinatorFile = classLoader.getResource("oozie/coordinator.xml").getPath
    println("workflowFile: " + workflowFile)
    println("coordinatorFile: " + coordinatorFile)
    val path = genAppPath(modelId)
    println("path: " + path)
    HDFSUtils.getInstance().mkdir(path)
    HDFSUtils.getInstance().copyFromFile(workflowFile, path + "/workflow.xml")
    HDFSUtils.getInstance().copyFromFile(coordinatorFile, path + "/coordinator.xml")
  }

  def genAppPath(modelId: Long): String = {
    ConfigHolder.model.path.modelBase + "/tags_" + modelId
  }

  def store(modelId: Long, prop: Properties): Unit = {
    val appPath = genAppPath(modelId)
    prop.store(HDFSUtils.getInstance().createFile(appPath + "/job.properties"), "")
  }

  def start(prop: Properties): String = {
    val oozie = new OozieClient(ConfigHolder.oozie.url)
    val jobId = oozie.run(prop)
    jobId
  }

  /**
   * 停止调度
   * @param jobId
   */
  def stop(jobId:String) :Unit ={
    //val oozie: OozieClient = new OozieClient(ConfigHolder.oozie.url)
    System.setProperty("user.name", "root")
    val oozie: AuthOozieClient = new AuthOozieClient(ConfigHolder.oozie.url, AuthOozieClient.AuthType.KERBEROS.name())
    //val oozie: AuthOozieClient = new AuthOozieClient(ConfigHolder.oozie.url,"root")
    oozie.kill(jobId)
  }
  /**
   * 调用方式展示
   */
  def main(args: Array[String]): Unit = {
    val param = OozieParam(
      1,
      "com.chb.tags.model.SimpleModel",
      "/tmp/jars/model-0.1.0.jar",
      "",
      "2019-09-24T06:15Z",
      "2019-09-30T06:15Z"
    )
    val prop = genProperties(param)
    println(prop)
    uploadConfig(param.modelId)
    store(param.modelId, prop)
    start(prop)
  }
}
