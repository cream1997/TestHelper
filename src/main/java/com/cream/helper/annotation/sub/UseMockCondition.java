package com.cream.helper.annotation.sub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class UseMockCondition implements Condition {

    private final static Set<String> alreadyLogged = new HashSet<>();

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        Boolean useMock = environment.getProperty("app-setup.use-mock-component", Boolean.class);
        if (Boolean.TRUE.equals(useMock)) {
            String componentName = metadata.toString();
            if (!alreadyLogged.contains(componentName)) {
                log.warn("项目开启了mock组件：{}", componentName);
                alreadyLogged.add(componentName);
            }
            return true;
        } else {
            return false;
        }
    }
}
