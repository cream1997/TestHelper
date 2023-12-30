package com.cream.helper.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cream.helper.utils.Times;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

@Configuration
public class MybatisPlusConfigurer implements MetaObjectHandler {

    private static final String createTime = "createTime";
    private static final String updateTime = "updateTime";

    /**
     * 插入时的填充策略
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        long now = Times.now();
        this.setFieldValByName(createTime, new Timestamp(now), metaObject);
        this.setFieldValByName(updateTime, new Timestamp(now), metaObject);
    }

    /**
     * 更新时的填充策略
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(updateTime, new Timestamp(Times.now()), metaObject);
    }
}
