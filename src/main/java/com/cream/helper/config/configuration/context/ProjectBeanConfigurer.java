package com.cream.helper.config.configuration.context;

import com.cream.helper.constant.AppConst;
import com.cream.helper.core.net.common.MsgTemplatePool;
import com.cream.helper.core.net.common.constant.MsgType;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.msg.base.MessageWithoutData;
import com.cream.helper.utils.ClassUtil;
import com.cream.helper.utils.Util;
import com.cream.helper.utils.msg.MsgClassUtil;
import com.google.protobuf.GeneratedMessageV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Configuration
public class ProjectBeanConfigurer {

    private final List<Message<?>> allReqTemplate = new ArrayList<>();

    private final List<Message<?>> allResMTemplate = new ArrayList<>();

    public ProjectBeanConfigurer() {
        registerMsgTemplate();
        check(MsgType.Req);
        check(MsgType.Res);
    }

    @SuppressWarnings("all")
    private void registerMsgTemplate() {
        List<Class<? extends Message>> allMsgClass = ClassUtil.findClasses(AppConst.MOCK_MSG_PKG, Message.class);
        for (Class<? extends Message> msgClass : allMsgClass) {
            Message<?> message = MsgClassUtil.newMsgInstance((Class<? extends Message<?>>) msgClass);
            if (msgClass != MessageWithoutData.class) {
                // 消息体赋默认值, 目的是让json能解析出格式
                setDefaultMsgData(message);
            }
            MsgType msgType = message.getMsgMeta().msgType;
            if (msgType == MsgType.Req) {
                allReqTemplate.add(message);
            } else if (msgType == MsgType.Res) {
                allResMTemplate.add(message);
            }
        }
    }

    private void setDefaultMsgData(Message<?> message) {
        Class<?> dataClass = message.getDataClass();
        if (GeneratedMessageV3.class.isAssignableFrom(dataClass)) {
            Class<GeneratedMessageV3> protoClass = (Class<GeneratedMessageV3>) dataClass;
            GeneratedMessageV3 dataInstance = MsgClassUtil.getDataInstance(protoClass);
            message.setDataFromGV3(dataInstance);
        }
    }

    private void check(MsgType msgType) {
        Set<Integer> msgIds = new HashSet<>();
        List<Message<?>> checkList = msgType == MsgType.Req ? allReqTemplate : allResMTemplate;
        for (Message<?> msgTemplate : checkList) {
            int msgId = msgTemplate.getMsgId();
            if (msgTemplate.getType() != msgType) {
                log.error("消息类型错误 class:{}, msgId:{}", msgTemplate.getClass(), msgId);
                Util.errExit();
            }
            if (msgIds.contains(msgId)) {
                log.error("消息重复msgId:{}, msgName:{}", msgId, msgTemplate.getClass().getSimpleName());
                Util.errExit();
            }
            msgIds.add(msgTemplate.getMsgId());
        }
    }

    @Bean
    public MsgTemplatePool messageTemplatePool() {
        return new MsgTemplatePool(allReqTemplate, allResMTemplate);
    }
}
