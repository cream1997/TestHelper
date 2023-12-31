package com.cream.helper.annotation;

import com.cream.helper.annotation.sub.UseMockCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Conditional(UseMockCondition.class)
@Component
public @interface MockComponent {
    boolean use() default true;
}
