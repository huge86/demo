package com.pingpukj.tags.models.statistics

import com.pingpukj.tags.ModelType
import com.pingpukj.tags.models.AbstractModel
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.IntegerType

/**
 * @Author: chb
 * @Date: 2021/4/25 8:59
 * @E-Mail:
 * @DESC:  标签开发：统计型标签->年龄段
 */
class AgeRangeModel extends AbstractModel("年龄段标签", ModelType.STATISTICS) {
  /**
   * 4. 构建标签：依据业务数据和属性标签数据建立标签
   *
   * @param businessDF
   * @param tagDF
   * @return
   */
  override def doTag(businessDF: DataFrame, tagDF: DataFrame): DataFrame = {
    // 导入隐式转换
    import businessDF.sparkSession.implicits._
    // 导入函数库
    import org.apache.spark.sql.functions._

    // 1. 自定UDF函数，解析分解属性标签的规则rule： 19500101-19591231
    val rule_to_tuple: UserDefinedFunction = udf(
      (rule: String) => {
        val Array(start, end) = rule.split("-").map(_.toInt)
        // 返回二元组
        (start, end)
      }
    )

    // 2. 获取属性标签数据，解析规则rule
    val attrTagRuleDF: DataFrame = tagDF
      .filter($"level" === 5) // 5级标签
      .select(
        $"name", //
        rule_to_tuple($"rule").as("rules") //
      )
      // 获取起始start和结束end
      .select(
        $"name", //
        $"rules._1".as("start"), //
        $"rules._2".as("end") //
      )
    //attrTagRuleDF.show(20, truncate = false)


    // 3. 业务数据与标签规则关联JOIN，比较范围
    /*
    attrTagDF： attr
    businessDF: business
    SELECT t2.userId, t1.name FROM attr t1 JOIN business t2
    WHERE t1.start <= t2.birthday AND t1.end >= t2.birthday ;
    */
    // 3.1. 转换日期格式： 1982-01-11 -> 19820111
    val birthdayDF: DataFrame = businessDF
      .select(
        $"id".as("userId"), //
        regexp_replace($"birthday", "-", "") //
          .cast(IntegerType).as("bornDate")
      )
    // 3.2. 关联属性规则，设置条件
    val modelDF: DataFrame = birthdayDF.join(attrTagRuleDF) // 关联
      .where( // 设置关联条件，在... 范围之内
        birthdayDF("bornDate").between(attrTagRuleDF("start"), attrTagRuleDF("end"))
      )
      // 选取字段
      .select($"userId", $"name".as("agerange"))
    // modelDF.printSchema()
    // modelDF.show(20, truncate = false)

    modelDF
  }
}


object AgeRangeModel {
  def main(args: Array[String]): Unit = {
    val tagModel = new AgeRangeModel()
    tagModel.executeModel(338)
  }
}