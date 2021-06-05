package com.pingpukj.tags.models.statistics

import com.pingpukj.tags.ModelType
import com.pingpukj.tags.models.AbstractModel
import com.pingpukj.tags.utils.TagTools
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.expressions.Window

/**
 * @Author: chb
 * @Date: 2021/4/29 8:56
 * @E-Mail:
 * @DESC:
 */
class PayTypeModel extends AbstractModel("支付方式标签", ModelType.STATISTICS) {
  /*
  356 支付方式
  357 支付宝 alipay
  358 微信支付 wxpay
  359 银联支付 chinapay
  360 货到付款 cod
  */
  override def doTag(businessDF: DataFrame, tagDF: DataFrame): DataFrame
  = {
    // 导入隐式转换
    import businessDF.sparkSession.implicits._
    import org.apache.spark.sql.functions._

    // 1. 订单数据中获取每个用户最多支付方式
    val paymentDF: DataFrame = businessDF
      // 按照会员ID和支付编码分组，统计次数
      .groupBy($"memberid", $"paymentcode")
      .count()
      // 获取每个会员支付方式最多，使用开窗函数ROW_NUMBER
      .withColumn(
        "rnk", //
        row_number().over(
          Window.partitionBy($"memberid").orderBy($"count".desc)
        )
      )
      // 过滤rnk等于1数据
      .where($"rnk".equalTo(1))
      .select(
        $"memberid".as("id"), //
        $"paymentcode".as("payment") //
      )
    // 2. 计算标签，规则匹配
    val modelDF: DataFrame = TagTools.ruleMatchTag(
      paymentDF, "payment", tagDF
    )
    //modelDF.show(100, truncate = false)
    // 3. 返回标签数据
    modelDF
  }
}


object PayTypeModel {
  def main(args: Array[String]): Unit = {
    val tagModel = new PayTypeModel()
    tagModel.executeModel(356L)
  }
}