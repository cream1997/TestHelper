package com.cream.helper.config.configuration.context;

import com.cream.helper.core.net.common.MessageTemplatePool;
import com.cream.helper.core.net.common.constant.MsgType;
import com.cream.helper.core.net.common.msg.ReqLoginMsg;
import com.cream.helper.core.net.common.msg.ResLoginMsg;
import com.cream.helper.core.net.common.msg.base.Message;
import com.cream.helper.utils.Util;
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

    private final List<Message<? extends GeneratedMessageV3>> allReqTemplate = new ArrayList<>();

    private final List<Message<? extends GeneratedMessageV3>> allResMTemplate = new ArrayList<>();

    public ProjectBeanConfigurer() {
        registerReq();
        registerRes();
        check(MsgType.Req);
        check(MsgType.Res);
    }

    private void check(MsgType msgType) {
        Set<Integer> msgIds = new HashSet<>();
        for (Message<? extends GeneratedMessageV3> reqMsgTemplate : allReqTemplate) {
            int msgId = reqMsgTemplate.getMsgId();
            if (reqMsgTemplate.getType() != msgType) {
                log.error("消息类型错误 class:{}, msgId:{}", reqMsgTemplate.getClass(), msgId);
                Util.errExit();
            }
            if (msgIds.contains(msgId)) {
                log.error("消息重复msgId:{}", msgId);
                Util.errExit();
            }
            msgIds.add(reqMsgTemplate.getMsgId());
        }
    }

    @Bean
    public MessageTemplatePool messageTemplatePool() {
        return new MessageTemplatePool(allReqTemplate, allResMTemplate);
    }

    /**
     * 注册请求消息协议
     */
    private void registerReq() {
        allReqTemplate.add(new ReqLoginMsg());
    }

    /**
     * 注册相应消息协议
     */
    private void registerRes() {
        allResMTemplate.add(new ResLoginMsg());
    }
}
