package cn.huzunjie.transformation;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * Author: HuZunJie
 * Date: 2021-06-03 16:58
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc: 演示DataStream-Transformation-拆分(split)和选择(select)操作
 *  * 注意split和select在flink1.12中已经过期并移除了
 *  * 所以得使用outPutTag和process来实现
 *  * 需求:对流中的数据按照奇数和偶数拆分并选择
 */

//split就是将一个流分成多个流，select就是获取分流后对应的数据
//side outputs:可以使用process方法对流中数据进行处理，并针对不同的处理结果将数据收集到不同的OutputTag中


public class SideOutputDemo {

    public static void main(String[] args) throws Exception {
        //TODO 0.env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);

        //TODO 1.source
        DataStreamSource<Integer> ds = env.fromElements(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //TODO 2.transformation
        //需求:对流中的数据按照奇数和偶数拆分并选择
        OutputTag<Integer> oddTag = new OutputTag<>("奇数", TypeInformation.of(Integer.class));
        OutputTag<Integer> evenTag = new OutputTag<>("偶数",TypeInformation.of(Integer.class));

        /*
        public abstract class ProcessFunction<I, O> extends AbstractRichFunction {
            public abstract void processElement(I value, ProcessFunction.Context ctx, Collector<O> out) throws Exception;
        }
         */
        SingleOutputStreamOperator<Integer> result = ds.process(new ProcessFunction<Integer, Integer>() {
            @Override
            public void processElement(Integer value, Context ctx, Collector<Integer> out) throws Exception {
                //out收集完的还是放在一起的,ctx可以将数据放到不同的OutputTag
                if (value % 2 == 0) {
                    ctx.output(evenTag, value);
                } else {
                    ctx.output(oddTag, value);
                }
            }
        });

        DataStream<Integer> oddResult = result.getSideOutput(oddTag);
        DataStream<Integer> evenResult = result.getSideOutput(evenTag);

        //TODO 3.sink
        System.out.println(oddTag);//OutputTag(Integer, 奇数)
        System.out.println(evenTag);//OutputTag(Integer, 偶数)
        oddResult.print("奇数:");
        evenResult.print("偶数:");

        //TODO 4.execute
        env.execute();
    }
}
