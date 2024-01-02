package com.cream.helper.annotation.sub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Slf4j
public class UseMockCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        Boolean useMock = environment.getProperty("project.use-mock-component", Boolean.class);
        if (Boolean.TRUE.equals(useMock)) {
            log.warn("项目开启了mock组件{}", metadata);
            return true;
        } else {
            return false;
        }
    }
}
