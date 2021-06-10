package com.xiaoge.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author NieChangan
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());

        // 起始版本 3.3.0(推荐使用)
        //在执行增加操作的时候,不仅仅要给createTime赋值,而且要给modifiedTime赋值
        this.strictInsertFill(metaObject, "modifiedTime", Date.class, new Date());

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");

        // 起始版本 3.3.0(推荐)
        this.strictUpdateFill(metaObject, "modifiedTime", Date.class, new Date());
    }
}
