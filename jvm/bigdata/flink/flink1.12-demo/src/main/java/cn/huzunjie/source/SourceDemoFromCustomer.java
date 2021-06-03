package cn.huzunjie.source;

import java.util.Random;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

/**
 * Author: HuZunJie
 * Date: 2021-06-03 9:30
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc:演示DataStream-Source-自定义数据源
 */

//需求：每隔一秒随机生成一条订单信息（订单id,用户id,订单金额，时间戳）
//要求：
//-随机生成订单ID（uuid）
//-随机生成用户ID（0-2）
//-随机生成订单金额（0-100）
//-时间戳为当前系统时间

public class SourceDemoFromCustomer {

    public static void main(String[] args) throws Exception {
        //TODO 0.env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);

        //TODO 1.source
        DataStream<Order> orderDS = env.addSource(new MyOrderSource()).setParallelism(2);

        //TODO 2.transformation

        //TODO 3.sink
        orderDS.print();

        //TODO 4.execute
        env.execute();
    }

//    domain object
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Order{
        private String id;
        private Integer userId;
        private Integer money;
        private Long createTime;
    }

    //自定义对象 类型的 数据生成器
    public static class MyOrderSource extends RichParallelSourceFunction<Order>{
        private Boolean flag = true;
        //执行并生成数据
        @Override
        public void run(SourceContext<Order> ctx) throws Exception {
            Random random = new Random();
            while (flag) {
                String oid = UUID.randomUUID().toString();
                int userId = random.nextInt(3);
                int money = random.nextInt(101);
                long createTime = System.currentTimeMillis();
                ctx.collect(new Order(oid,userId,money,createTime));
                Thread.sleep(1000);
            }
        }

        //执行cancel命令的时候执行
        @Override
        public void cancel() {
            flag = false;
        }
    }
}
