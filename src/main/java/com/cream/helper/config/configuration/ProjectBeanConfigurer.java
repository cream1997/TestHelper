package com.cream.helper.config.configuration;

import com.cream.helper.core.net.msg.Message;
import com.cream.helper.core.net.msg.MessageTemplatePool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProjectBeanConfigurer {

    @Bean
    public MessageTemplatePool messageTemplatePool() {
        // fixme mock impl
        // 请求模板
        List<Message> reqMsgTemplate = new ArrayList<>();
        Message reqMessage = new Message(111, Message.Type.Request);
        reqMsgTemplate.add(reqMessage);
        // 响应模板
        List<Message> resMsgTemplate = new ArrayList<>();
        Message resMessage = new Message(222, Message.Type.Response);
        resMsgTemplate.add(resMessage);

        return new MessageTemplatePool(reqMsgTemplate, resMsgTemplate);
    }

}
