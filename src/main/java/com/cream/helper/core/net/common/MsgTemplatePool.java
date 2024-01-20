package com.cream.helper.core.net.common;

import com.cream.helper.core.net.common.msg.base.Message;
import com.cream.helper.utils.NullUtil;
import com.cream.helper.utils.Util;
import com.google.protobuf.GeneratedMessageV3;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息模板池
 */
@Slf4j
public class MsgTemplatePool {

    private final Map<Integer, Message<? extends GeneratedMessageV3>> msgId2Template;

    @Getter
    private final List<Message<? extends GeneratedMessageV3>> allReqTemplate;
    @Getter
    private final List<Message<? extends GeneratedMessageV3>> allResTemplate;

    public MsgTemplatePool(List<Message<? extends GeneratedMessageV3>> allReqTemplate, List<Message<? extends GeneratedMessageV3>> allResTemplate) {
        if (NullUtil.isAnyEmpty(allResTemplate, allResTemplate)) {
            throw new IllegalArgumentException("传入值为空");
        }
        this.allReqTemplate = Collections.unmodifiableList(allReqTemplate);
        this.allResTemplate = Collections.unmodifiableList(allResTemplate);
        this.msgId2Template = initMsgId2Template();
    }

    private Map<Integer, Message<? extends GeneratedMessageV3>> initMsgId2Template() {
        HashMap<Integer, Message<? extends GeneratedMessageV3>> modifiableMap = new HashMap<>();
        putInMsgTemplate(modifiableMap, allReqTemplate);
        putInMsgTemplate(modifiableMap, allReqTemplate);
        return Collections.unmodifiableMap(modifiableMap);
    }

    private void putInMsgTemplate(Map<Integer, Message<? extends GeneratedMessageV3>> modifiableMap, List<Message<? extends GeneratedMessageV3>> msgTemplate) {
        for (Message<? extends GeneratedMessageV3> reqTemplate : msgTemplate) {
            modifiableMap.put(reqTemplate.getMsgId(), reqTemplate);
        }
    }

    public Message<? extends GeneratedMessageV3> getMsgTemplate(int msgId) {
        Message<? extends GeneratedMessageV3> msgTemplate = msgId2Template.get(msgId);
        if (msgTemplate == null) {
            log.error("未注册该消息; msgId:{} {}", msgId, Util.getStackTrace());
        }
        return msgTemplate;
    }

    @SuppressWarnings("rawtypes")
    public Class<? extends Message> getMsgClass(int msgId) {
        Message<? extends GeneratedMessageV3> msgTemplate = getMsgTemplate(msgId);
        if (msgTemplate == null) {
            return null;
        }
        return msgTemplate.getClass();
    }
}
