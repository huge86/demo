package cn.huzunjie.sink;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Author: HuZunJie
 * Date: 2021-06-03 20:40
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc: 演示DataStream-Sink-基于控制台
 */


public class ConsoleBasedDemo {

    public static void main(String[] args) throws Exception {
        //TODO 0.env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);
        env.setParallelism(1);

        //TODO 1.source
        DataStream<String> ds = env.readTextFile("data/input/words.txt");

        //TODO 2.transformation
        //TODO 3.sink
//        ds.print();//直接输出到控制台
//        ds.print("输出标识：");
        ds.printToErr();//会在控制台上以红色输出
//        ds.printToErr("输出标识");//会在控制台上以红色输出


        //TODO 4.execute
        env.execute();
    }
}
