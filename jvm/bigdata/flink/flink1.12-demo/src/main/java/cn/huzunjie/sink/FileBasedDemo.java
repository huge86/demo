package cn.huzunjie.sink;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Author: HuZunJie
 * Date: 2021-06-03 20:40
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc: 演示DataStream-Sink-基于文件
 */


public class FileBasedDemo {

    public static void main(String[] args) throws Exception {
        //TODO 0.env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);
        env.setParallelism(1);

        //TODO 1.source
        DataStream<String> ds = env.readTextFile("data/input/words.txt");

        //TODO 2.transformation
        //TODO 3.sink
        //格式：ds.writeAsText("本地/HDFS的path", FileSystem.WriteMode.OVERWRITE).setParallelism(1)
//        在输出path的时候，可以在前面设置并行度，如果并行度>1,则path为目录；如果并行度=1，则path为文件名
//        ds.writeAsText("data/output/result1").setParallelism(1);
        ds.writeAsText("data/output/result2", FileSystem.WriteMode.OVERWRITE).setParallelism(2);

        //TODO 4.execute
        env.execute();
    }
}
