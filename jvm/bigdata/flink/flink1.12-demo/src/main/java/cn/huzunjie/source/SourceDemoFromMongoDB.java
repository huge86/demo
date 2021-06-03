package cn.huzunjie.source;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: HuZunJie
 * Date: 2021-06-03 9:30
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc:演示DataStream-Source-自定义数据源-MySQL
 */

//需求：统计MongoDB数据库集合中记录数量





public class SourceDemoFromMongoDB {

    public static void main(String[] args) throws Exception {
        //TODO 0.env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);

        //TODO 1.source
        DataStream<Long> studentDS = env.addSource(new MongoDBSource()).setParallelism(1);

        //TODO 2.transformation

        //TODO 3.sink
        studentDS.print();

        //TODO 4.execute
        env.execute();
    }


//    自定义Student类型的source
    public static class MongoDBSource extends RichParallelSourceFunction<Long> {
        private boolean flag = true;
        private MongoClient mongoClient = null;
        private MongoDatabase mongoDatabase = null;
        private MongoCollection<Document> mc = null;


    //open只执行一次,适合开启资源
        @Override
        public void open(Configuration parameters) throws Exception {
    //根据实际环境修改ip和端口
            //不需要密码认证
            //mongoClient = new MongoClient("localhost", 27017);

            //需要密码认证
            List<ServerAddress> adds = new ArrayList<>();
            //ServerAddress()两个参数分别为 服务器地址 和 端口
            ServerAddress serverAddress = new ServerAddress("103.89.185.87", 27017);
            adds.add(serverAddress);
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
            MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("pingpu", "security_enterprise_n2", "pingpu@123".toCharArray());
            credentials.add(mongoCredential);
            //通过连接认证获取MongoDB连接
            MongoClient mongoClient = new MongoClient(adds, credentials);
            mongoDatabase = mongoClient.getDatabase("security_enterprise_n2");
            mc = mongoDatabase.getCollection("busEquitydetailsDTO");

        }

        @Override
        public void run(SourceContext<Long> ctx) throws Exception {
            while (flag) {
                ctx.collect(mc.countDocuments());

                Thread.sleep(50);
            }

        }

        //接收到cancel命令时取消数据生成
        @Override
        public void cancel() {
            flag = false;
        }

        //close里面关闭资源
        @Override
        public void close() {
            mongoClient.close();
        }
    }

}
