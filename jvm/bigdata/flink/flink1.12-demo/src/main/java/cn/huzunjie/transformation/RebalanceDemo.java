package cn.huzunjie.transformation;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Author: HuZunJie
 * Date: 2021-06-03 19:20
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc: 演示DataStream-Transformation-rebalance-重平衡分区
 */

//类似spark中的repartiton,但是功能更加强大，可以直接解决数据倾斜，Flink也有数据倾斜的时候
public class RebalanceDemo {

    public static void main(String[] args) throws Exception {
        //TODO 0.env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);

        //TODO 1.source
        DataStream<Long> longDS = env.fromSequence(0, 100);
        //下面的操作相当于将数据随机分配一下,有可能出现数据倾斜
        DataStream<Long> filterDS = longDS.filter(new FilterFunction<Long>() {
            @Override
            public boolean filter(Long num) throws Exception {
                return num > 10;
            }
        });

        //TODO 2.transformation
        //没有经过rebalance有可能出现数据倾斜
        SingleOutputStreamOperator<Tuple2<Integer, Integer>> result1 = filterDS
                .map(new RichMapFunction<Long, Tuple2<Integer, Integer>>() {
                    @Override
                    public Tuple2<Integer, Integer> map(Long value) throws Exception {
                        int subTaskId = getRuntimeContext().getIndexOfThisSubtask();//子任务id/分区编号
                        return Tuple2.of(subTaskId, 1);
                    }
                    //按照子任务id/分区编号分组,并统计每个子任务/分区中有几个元素
                }).keyBy(t -> t.f0).sum(1);

        //调用了rebalance解决了数据倾斜
        SingleOutputStreamOperator<Tuple2<Integer, Integer>> result2 = filterDS.rebalance()
                .map(new RichMapFunction<Long, Tuple2<Integer, Integer>>() {
                    @Override
                    public Tuple2<Integer, Integer> map(Long value) throws Exception {
                        int subTaskId = getRuntimeContext().getIndexOfThisSubtask();//子任务id/分区编号
                        return Tuple2.of(subTaskId, 1);
                    }
                    //按照子任务id/分区编号分组,并统计每个子任务/分区中有几个元素
                }).keyBy(t -> t.f0).sum(1);


        //TODO 3.sink
//        result1.print("result1");
        result2.print("result2");


        //TODO 4.execute
        env.execute();
    }
}
