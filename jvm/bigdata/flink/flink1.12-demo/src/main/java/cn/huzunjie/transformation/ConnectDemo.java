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
 * Desc: 演示DataStream-Transformation-connect操作
 */

//connect提供了和union类似的功能，用来连接两个数据流，他与union的区别在于connect只能连接两个数据流，union算子可以连接
// 多个数据流，connect所连接的两个数据流的数据类型可以不一致，union所连接的多个数据流的类型必须一致。
// 两个DataStream经过connect之后，被转化为ConnectedStreams,ConnectedStreams会对两个流的数据应用不同的处理方法，
// 且双流之间，可以共享状态。
public class ConnectDemo {

    public static void main(String[] args) throws Exception {
        //TODO 0.env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);

        //TODO 1.source
        DataStream<String> ds1 = env.fromElements("hadoop", "spark", "flink");
        DataStream<String> ds2 = env.fromElements("aaa", "bbb", "ccc");
        DataStream<Long> ds3 = env.fromElements(1L, 2L, 3L);

        //TODO 2.transformation
        ConnectedStreams<String, String> result2 = ds1.connect(ds2);//注意:connet可以合并同类型
        ConnectedStreams<String, Long> result3 = ds1.connect(ds3);//注意connet可以合并不同类型

        /*
        public interface CoMapFunction<IN1, IN2, OUT> extends Function, Serializable {
            OUT map1(IN1 value) throws Exception;
            OUT map2(IN2 value) throws Exception;
        }
         */
        SingleOutputStreamOperator<String> result = result3.map(new CoMapFunction<String, Long, String>() {
            @Override
            public String map1(String value) throws Exception {
                return "String:" + value;
            }

            @Override
            public String map2(Long value) throws Exception {
                return "Long:" + value;
            }
        });


        //TODO 3.sink
        //result2.print();//注意:connect之后需要做其他的处理,不能直接输出
        //result3.print();//注意:connect之后需要做其他的处理,不能直接输出
        result.print();

        //TODO 4.execute
        env.execute();
    }
}
