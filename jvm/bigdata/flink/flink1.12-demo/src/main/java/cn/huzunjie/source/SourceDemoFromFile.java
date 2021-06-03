package cn.huzunjie.source;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Author: HuZunJie
 * Date: 2021-06-03 9:30
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc:演示DataStream-Source-基于本地/HDFS的文件/文件夹/压缩文件
 */


public class SourceDemoFromFile {

    public static void main(String[] args) throws Exception {
        //TODO 0.env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);

        //TODO 1.source
        DataStreamSource<String> stringDataStreamSource = env.readTextFile("data/words.txt");

//        DataStream<String> ds1 = env.readTextFile("data/input/words.txt");
        DataStream<String> ds2 = env.readTextFile("data/input/dir");
        DataStream<String> ds3 = env.readTextFile("data/input/wordcount.txt.gz");


        //TODO 2.transformation

        //TODO 3.sink
        stringDataStreamSource.print();
//        ds1.print();
        ds2.print();
        ds3.print();

        //TODO 4.execute
        env.execute();
    }
}
