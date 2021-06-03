package cn.huzunjie.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Author: HuZunJie
 * Date: 2021-06-03 9:30
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc:演示DataStream-Source-自定义数据源-MySQL
 */

//需求：统计MySQL数据库表中记录数量
//思路：
//1、连接MySQL数据库，
//2、获取初始化参数：当前表中总记录数：total，当前最大时间戳：timestamp
//3、total缓存到redis,然后查询出表中比当前时间戳大的记录数，发送到kafka生产者，；
//    flink消费kafka中的

//    不用大数据也能解决的方法：当前表中总记录数：total，当前最大时间戳：timestamp，
//    通过一个while循环，total缓存到redis,然后查询出表中比当前时间戳大的记录数n，更新：total+n,更新最大时间戳




public class SourceDemoFromMySQL {

    public static void main(String[] args) throws Exception {
        //TODO 0.env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);

        //TODO 1.source
        DataStream<Integer> studentDS = env.addSource(new MySQLSource()).setParallelism(1);

        //TODO 2.transformation

        //TODO 3.sink
        studentDS.print();

        //TODO 4.execute
        env.execute();
    }

   /*
   CREATE TABLE `t_student` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `age` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

INSERT INTO `t_student` VALUES ('1', 'jack', '18');
INSERT INTO `t_student` VALUES ('2', 'tom', '19');
INSERT INTO `t_student` VALUES ('3', 'rose', '20');
INSERT INTO `t_student` VALUES ('4', 'tom', '19');
INSERT INTO `t_student` VALUES ('5', 'jack', '18');
INSERT INTO `t_student` VALUES ('6', 'rose', '20');
    */

//    domain,lombok注解
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Student {
        private Integer id;
        private String name;
        private Integer age;
    }

//    自定义Student类型的source
    public static class MySQLSource extends RichParallelSourceFunction<Integer> {
        private boolean flag = true;
        private Connection conn = null;
        private PreparedStatement ps =null;
        private ResultSet rs  = null;
        //open只执行一次,适合开启资源
        @Override
        public void open(Configuration parameters) throws Exception {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
            String sql = "select id,name,age from t_student";
            ps = conn.prepareStatement(sql);
        }

        @Override
        public void run(SourceContext<Integer> ctx) throws Exception {
            while (flag) {
                rs = ps.executeQuery();//执行查询
//                JDBC中的ResultSet API没有直接获取记录条数的方法,参考：https://blog.csdn.net/guozuofeng/article/details/103215551
                rs.last();
                int rows = rs.getRow();
                //System.out.println(rows);
//                统计表中记录数
                ctx.collect(rows);
                Thread.sleep(5000);
            }
        }

        //接收到cancel命令时取消数据生成
        @Override
        public void cancel() {
            flag = false;
        }

        //close里面关闭资源
        @Override
        public void close() throws Exception {
            if(conn != null) conn.close();
            if(ps != null) ps.close();
            if(rs != null) rs.close();
        }
    }

}
