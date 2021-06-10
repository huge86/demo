package com.xiaoge.system.service.impl;

import com.xiaoge.system.entity.Log;
import com.xiaoge.system.mapper.LogMapper;
import com.xiaoge.system.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author xiaoge
 * @since 2020-09-04
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
