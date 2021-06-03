package cn.huzunjie.example;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;

/**
 * Author: HuZunJie
 * Date: 2021-06-03 7:16
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc:演示Flink-DataStream-API-实现WordCount
 *  * 注意:在Flink1.12中DataStream既支持流处理也支持批处理,如何区分?
 */


public class WordCountByDataStreamOnYarn {
    public static void main(String[] args) throws Exception {
        ParameterTool parameterTool = ParameterTool.fromArgs(args);
        String output = "";
        if (parameterTool.has("output")) {
            output = parameterTool.get("output");
            System.out.println("指定了输出路径使用:" + output);
        } else {
            output = "hdfs://node1:8020/wordcount/output47_";
            System.out.println("可以指定输出路径使用 --output ,没有指定使用默认的:" + output);
        }

        //TODO 0.env
        //ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //env.setRuntimeMode(RuntimeExecutionMode.BATCH);//注意:使用DataStream实现批处理
        //env.setRuntimeMode(RuntimeExecutionMode.STREAMING);//注意:使用DataStream实现流处理
        //env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);//注意:使用DataStream根据数据源自动选择使用流还是批

        //TODO 1.source
        //DataSet<String> lines = env.fromElements("itcast hadoop spark", "itcast hadoop spark", "itcast hadoop", "itcast");
        DataStream<String> lines = env.fromElements("itcast hadoop spark", "itcast hadoop spark", "itcast hadoop", "itcast");

        //TODO 2.transformation
        //切割
        /*
        @FunctionalInterface
        public interface FlatMapFunction<T, O> extends Function, Serializable {
            void flatMap(T value, Collector<O> out) throws Exception;
        }
         */
        /*DataStream<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out) throws Exception {
                //value就是每一行数据
                String[] arr = value.split(" ");
                for (String word : arr) {
                    out.collect(word);
                }
            }
        });*/
        SingleOutputStreamOperator<String> words = lines.flatMap(
                (String value, Collector<String> out) -> Arrays.stream(value.split(" ")).forEach(out::collect)
        ).returns(Types.STRING);


        //记为1
        /*
        @FunctionalInterface
        public interface MapFunction<T, O> extends Function, Serializable {
            O map(T value) throws Exception;
        }
         */
        /*DataStream<Tuple2<String, Integer>> wordAndOne = words.map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String value) throws Exception {
                //value就是一个个单词
                return Tuple2.of(value, 1);
            }
        });*/
        DataStream<Tuple2<String, Integer>> wordAndOne = words.map(
                (String value) -> Tuple2.of(value, 1)
        ).returns(Types.TUPLE(Types.STRING, Types.INT));

        //分组:注意DataSet中分组是groupBy,DataStream分组是keyBy
        //wordAndOne.keyBy(0);
        /*
        @FunctionalInterface
        public interface KeySelector<IN, KEY> extends Function, Serializable {
            KEY getKey(IN value) throws Exception;
        }
         */
        KeyedStream<Tuple2<String, Integer>, String> grouped = wordAndOne.keyBy(t -> t.f0);

        //聚合
        SingleOutputStreamOperator<Tuple2<String, Integer>> result = grouped.sum(1);

        //TODO 3.sink
        //如果执行报hdfs权限相关错误,可以执行 hadoop fs -chmod -R 777  /
        System.setProperty("HADOOP_USER_NAME", "root");//设置用户名
        //result.print();
        //result.writeAsText("hdfs://node1:8020/wordcount/output47_"+System.currentTimeMillis()).setParallelism(1);
        result.writeAsText(output + System.currentTimeMillis()).setParallelism(1);

        //TODO 4.execute/启动并等待程序结束
        env.execute();
    }
}
