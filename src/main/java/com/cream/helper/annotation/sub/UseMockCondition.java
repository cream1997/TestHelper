package com.cream.helper.annotation.sub;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class UseMockCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        Boolean useMock = environment.getProperty("project.use-mock-component", Boolean.class);
        if (Boolean.TRUE.equals(useMock)) {
            // todo 替换为使用日志框架
            System.err.println("项目开启了mock组件");
            return true;
        } else {
            return false;
        }
    }
}
