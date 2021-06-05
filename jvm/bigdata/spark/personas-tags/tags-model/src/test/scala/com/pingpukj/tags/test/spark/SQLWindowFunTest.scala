package com.pingpukj.tags.test.spark

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @Author: chb
 * @Date: 2021/4/29 8:52
 * @E-Mail:
 * @DESC: SparkSQL中开窗函数DSL编程
 */
object SQLWindowFunTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(this.getClass.getSimpleName.stripSuffix("$"))
      .master("local[4]")
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      // 设置Shuffle分区数目
      .config("spark.sql.shuffle.partitions", "4")
      // 设置与Hive集成: 读取Hive元数据MetaStore服务
      .config("hive.metastore.uris", "thrift://chb1:9083")
      // 设置数据仓库目录
      .config("spark.sql.warehouse.dir", "hdfs://chb2:8020/user/hive/warehouse")
      .enableHiveSupport()
      .getOrCreate()
    import org.apache.spark.sql.functions._
    import spark.implicits._
    // 1. 从Hive表中读取雇员表数据[db_hive.emp]
    val empDF: DataFrame = spark.read
      .table("db_hive.emp")
      .select($"empno", $"ename", $"sal", $"deptno")

    /*
    root
    |-- empno: integer (nullable = true)
    |-- ename: string (nullable = true)
    |-- sal: double (nullable = true)
    |-- deptno: integer (nullable = true)
    */
    //empDF.printSchema()
    /*
    +-----+------+------+------+
    |empno|ename |sal |deptno|
    +-----+------+------+------+
    |7369 |SMITH |800.0 |20 |
    |7499 |ALLEN |1600.0|30 |
    |7521 |WARD |1250.0|30 |
    |7566 |JONES |2975.0|20 |
    |7654 |MARTIN|1250.0|30 |
    |7698 |BLAKE |2850.0|30 |
    |7782 |CLARK |2450.0|10 |
    |7788 |SCOTT |3000.0|20 |
    |7839 |KING |5000.0|10 |
    |7844 |TURNER|1500.0|30 |
    |7876 |ADAMS |1100.0|20 |
    |7900 |JAMES |950.0 |30 |
    |7902 |FORD |3000.0|20 |
    |7934 |MILLER|1300.0|10 |
    +-----+------+------+------+
    */

    //empDF.show(20, truncate = false)
    // 2. 需求：各个部门工资最高的人员信息，使用开窗函数row_number
    // 方式一：使用SQL实现
    // a. 注册DataFrame为临时视图
    empDF.createOrReplaceTempView("view_tmp_emp")
    // b. 编写SQL
    spark.sql(
      """
        |WITH tmp AS (
        |SELECT
        | *,
        | ROW_NUMBER() OVER(PARTITION BY deptno ORDER BY sal DESC)  AS rnk
        |FROM
        | view_tmp_emp
        |)
        |SELECT
        | t.empno, t.ename, t.sal, t.deptno
        |FROM tmp t
        |WHERE t.rnk = 1
        |""".stripMargin)
      .show()
    /*
    +-----+-----+------+------+
    |empno|ename| sal|deptno|
    +-----+-----+------+------+
    | 7788|SCOTT|3000.0| 20|
    | 7698|BLAKE|2850.0| 30|
    | 7839| KING|5000.0| 10|
    +-----+-----+------+------+
    */
    println("=========================================")
    // 方式二：使用DSL编程实现
    empDF
      // 使用函数，增加一列
      .withColumn(
        "rnk", //
        row_number().over(
          Window.partitionBy($"deptno").orderBy($"sal".desc)
        )
      )
      // 获取rnk=1
      .where($"rnk" === 1)
      .select($"empno", $"ename", $"sal", $"deptno")
      .show()

  }
}
