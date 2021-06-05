package com.pingpukj.tags.oozie;

import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.OozieClientException;
import org.apache.oozie.client.WorkflowJob;

import java.util.Properties;

public class OozieWorkflowSubmit {
    public static void main(String[] args) throws OozieClientException, InterruptedException {
        String OOZIE_URL = OozieConstant.OOZIE_URL;
        // TODO: 1. 构建OozieClient 客户端实例对象
        OozieClient oozieClient = new OozieClient(OOZIE_URL);
        // TODO: 2. 设置Workflow相关配置参数值
        Properties jobConf = oozieClient.createConfiguration();
        // 2.1. 系统参数设置
        jobConf.setProperty("oozie.use.system.libpath", "true");
        jobConf.setProperty("user.name", "root");
        jobConf.setProperty("oozie.libpath", OozieConstant.HDFSROOTPATH + "/user/root/share/lib/lib_20190723215106/spark2");
        // 2.2. 必要参数信息
        jobConf.setProperty("nameNode", OozieConstant.HDFSROOTPATH);
        jobConf.setProperty("jobTracker", OozieConstant.jobTracker);
        jobConf.setProperty("queueName", "default");
        // 2.3. 应用提交运行yarn参数
        jobConf.setProperty("master", "yarn");
        jobConf.setProperty("mode", "cluster");
        jobConf.setProperty("sparkOptions", " --driver-memory 512m " + "--executor-memory 512m " + "--num-executors 1 " + "--executor-cores 1 " + "--conf spark.yarn.historyServer.address=http://chb1:18080 " + "--conf spark.eventLog.enabled=true " + "--conf spark.eventLog.dir=hdfs://chb1:8020/spark/eventLogs " + "--conf spark.yarn.jars=hdfs://chb1:8020/spark/jars/*");
        jobConf.setProperty("mainClass", "org.apache.spark.examples.SparkPi");
        jobConf.setProperty("appName", "SparkExamplePi");
        jobConf.setProperty("jarPath", OozieConstant.HDFSROOTPATH + "/user/root/oozie_works/cron_yarn_pi/lib/spark-examples_2.11-2.4.0-cdh6.2.0.jar");
        jobConf.setProperty("appParam", "10");
        // 2.4. Oozie Workflow 参数
        jobConf.setProperty(OozieClient.APP_PATH, OozieConstant.HDFSROOTPATH + "/user/root/oozie_works/cron_yarn_pi/workflow.xml");
        // TODO: 3. 提交执行Oozie Workflow，返回应用提交JobID
        String jobId = oozieClient.run(jobConf);
        System.out.println("JobId = " + jobId);
        // TODO: 4. 依据JobID获取转态信息
        while (oozieClient.getJobInfo(jobId).getStatus() == WorkflowJob.Status.RUNNING) {
            System.out.println("Workflow job running ...");
            Thread.sleep(10 * 1000);
        }
        System.out.println("Workflow job completed ...");
    }
}
