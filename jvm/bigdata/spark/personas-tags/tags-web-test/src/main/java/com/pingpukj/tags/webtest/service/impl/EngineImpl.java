package com.pingpukj.tags.webtest.service.impl;

import com.pingpukj.tags.up.OozieParam;
import com.pingpukj.tags.up.OozieUtils;
import com.pingpukj.tags.webtest.entity.dto.ModelDto;
import com.pingpukj.tags.webtest.service.Engine;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EngineImpl implements Engine {
    @Override
    public String startModel(ModelDto model) {
        // 设置动态的参数, 例如如何调度, 主类名, jar 的位置
        OozieParam param = new OozieParam(
                model.getId(),
                model.getMainClass(),
                model.getPath(),
                model.getArgs(),
                ModelDto.Schedule.formatTime(model.getSchedule().getStartTime()),
                ModelDto.Schedule.formatTime(model.getSchedule().getEndTime())
        );

        // 生成配置
        Properties properties = OozieUtils.genProperties(param);

        // 上传各种配置, workflow.xml, coordinator.xml
        OozieUtils.uploadConfig(model.getId());

        // 因为如果不保留一份 job.properties 的文件, 无法调试错误
        OozieUtils.store(model.getId(), properties);

        // 运行 Oozie 任务
        return OozieUtils.start(properties);
    }

    @Override
    public void stopModel(ModelDto model) {
        //注意:前面开启任务的时候将任务的id保存到了name中,所以停止任务的时候使用name也就是jobid来停止
        OozieUtils.stop(model.getName());
    }
}
