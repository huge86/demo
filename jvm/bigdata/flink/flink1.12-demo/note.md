## 1、pom.xml中可以配置maven仓库；

## 2、flink初体验
### 需求：使用flink实现wordcount

1. 准备环境-env
2. 准备数据-source
3. 处理数据-transformation
4. 输出结果-sink
5. 触发执行-execute
### 3、flink1.12开始批流一体
* dataset api软废弃
* dataset模式获取env:ExecutionEnvironment.getExecutionEnvironment()
* datastream模式获取env:StreamExecutionEnvironment.getExecutionEnvironment();

### 4、source

1. 基于集合的source，一般用于学习测试时编造数据时使用
* env.fromElements(可变参数)
* env.fromCollection(各种集合)
* env.generateSequence(开始，结束)
* env.fromSequence(开始，结束)

2. 基于文件的source，一般用于学习测试时编造数据时使用
    - env.readTextFile(本地/HDFS/文件夹);//压索文件也可以
   
3. 基于socket的source
4. 自定义source，flink提供了数据源借口，我们实现该接口，就可以实现自定义数据源
   + SourceFunction:非并行数据源，并行度只能=1
   + RichSourceFunction:多功能非并行数据源
   + ParallelSourceFunction:并行数据源，并行度>=1
   + RichParallelSourceFunction:多功能并行数据源,kafka使用的就是
   

