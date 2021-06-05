package com.pingpukj.tags.models.rule



import com.pingpukj.tags.ModelType
import com.pingpukj.tags.models.AbstractModel
import com.pingpukj.tags.utils.TagTools
import org.apache.spark.sql.DataFrame

/**
 * @Author: chb
 * @Date: 2021/4/23 16:12
 * @E-Mail:
 * @DESC:
 */
class JobTagModel extends AbstractModel("职业标签", ModelType.MATCH) {

  /*
    321 职业
    322 学生 1
    323 公务员 2
    324 军人 3
    325 警察 4
    326 职业是教师 5
    327 白领 6
  */

  /**
   * 4. 构建标签：依据业务数据和属性标签数据建立标签
   *
   * @param businessDF
   * @param tagDF
   * @return
   */
  override def doTag(businessDF: DataFrame, tagDF: DataFrame): DataFrame = {
    // 计算标签
    val modelDF: DataFrame = TagTools.ruleMatchTag(
      businessDF, "job", tagDF
    )
    // 返回
    modelDF
  }
}

object JobTagModel {
  def main(args: Array[String]): Unit = {
    val tagModel = new JobTagModel()
    tagModel.executeModel(321L, isHive = true)
  }
}
