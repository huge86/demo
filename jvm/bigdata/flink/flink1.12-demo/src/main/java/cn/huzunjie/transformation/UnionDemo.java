package cn.huzunjie.transformation;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;
/**
 * Author: HuZunJie
 * Date: 2021-06-03 16:29
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc: 演示DataStream-Transformation-union操作
 */

//union算子可以合并多个同类型的数据流，并生成同类型的数据流，即可以将多个DataStream[T]合并为一个新的DataStream[T].数据将按照先进先出合并
public class UnionDemo {

    public static void main(String[] args) throws Exception {
        //TODO 0.env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);

        //TODO 1.source
        DataStream<String> ds1 = env.fromElements("hadoop", "spark", "flink");
        DataStream<String> ds2 = env.fromElements("aaa", "bbb", "ccc");
        DataStream<Long> ds3 = env.fromElements(1L, 2L, 3L);

        //TODO 2.transformation
        DataStream<String> result1 = ds1.union(ds2);//注意union能合并同类型
        //ds1.union(ds3);//注意union不可以合并不同类型
        //ConnectedStreams<String> result2 = ds1.union(ds3);//注意:connet可以合并同类型


        /*
        public interface CoMapFunction<IN1, IN2, OUT> extends Function, Serializable {
            OUT map1(IN1 value) throws Exception;
            OUT map2(IN2 value) throws Exception;
        }
         */


        //TODO 3.sink
        result1.print();

        //TODO 4.execute
        env.execute();
    }
}
