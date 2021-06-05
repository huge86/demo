package com.pingpukj.tags.models.rule

import com.pingpukj.tags.models.BasicModel
import com.pingpukj.tags.utils.TagTools
import org.apache.spark.sql.DataFrame

/**
 * @Author: chb
 * @Date: 2021/4/23 17:15
 * @E-Mail:
 * @DESC: 标签模型开发：政治面貌标签模型
 */
class PoliticalModel extends BasicModel {
  /*
  328 政治面貌
  329 群众 1
  330 党员 2
  331 无党派人士 3
  */
  override def doTag(businessDF: DataFrame, tagDF: DataFrame): DataFrame
  = {
    // 计算标签
    val modelDF: DataFrame = TagTools.ruleMatchTag(
      businessDF, "politicalface", tagDF
    )
    //modelDF.printSchema()
    //modelDF.show(100, truncate = false)
    // 返回
    modelDF
  }
}

object PoliticalModel {
  def main(args: Array[String]): Unit = {
    val tagModel = new PoliticalModel()
    tagModel.executeModel(328)
  }
}

