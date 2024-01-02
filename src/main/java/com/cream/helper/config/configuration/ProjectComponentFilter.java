package com.cream.helper.config.configuration;


import com.cream.helper.annotation.MockComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cream on 2017/11/28.
 * fixme 使用ChatGPT实现的MockComponent注解处理程序，后续可能需要修改
 */
@Slf4j
@Configuration
public class ProjectComponentFilter implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        for (String beanName : registry.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
            if (beanDefinition instanceof AnnotatedBeanDefinition annotatedBeanDefinition) {
                String beanClassName = annotatedBeanDefinition.getBeanClassName();
                if (beanClassName == null || beanClassName.isEmpty()) {
                    continue;
                }
                try {
                    Class<?> beanClass = Class.forName(beanClassName);
                    MockComponent myComponent = beanClass.getAnnotation(MockComponent.class);

                    if (myComponent != null && !myComponent.use()) {
                        registry.removeBeanDefinition(beanName);
                    }
                } catch (ClassNotFoundException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }
}
