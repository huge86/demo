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


