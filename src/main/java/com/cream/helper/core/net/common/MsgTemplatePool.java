package com.cream.helper.core.net.common;

import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.utils.NullUtil;
import com.cream.helper.utils.Util;
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

    private final Map<Integer, Message<?>> msgId2Template;

    @Getter
    private final List<Message<?>> allReqTemplate;
    @Getter
    private final List<Message<?>> allResTemplate;

    public MsgTemplatePool(List<Message<?>> allReqTemplate, List<Message<?>> allResTemplate) {
        if (NullUtil.isAnyEmpty(allResTemplate, allResTemplate)) {
            throw new IllegalArgumentException("传入值为空");
        }
        this.allReqTemplate = Collections.unmodifiableList(allReqTemplate);
        this.allResTemplate = Collections.unmodifiableList(allResTemplate);
        this.msgId2Template = initMsgId2Template();
    }

    private Map<Integer, Message<?>> initMsgId2Template() {
        HashMap<Integer, Message<?>> modifiableMap = new HashMap<>();
        putInMsgTemplate(modifiableMap, allReqTemplate);
        putInMsgTemplate(modifiableMap, allReqTemplate);
        return Collections.unmodifiableMap(modifiableMap);
    }

    private void putInMsgTemplate(Map<Integer, Message<?>> modifiableMap, List<Message<?>> msgTemplate) {
        for (Message<?> reqTemplate : msgTemplate) {
            modifiableMap.put(reqTemplate.getMsgId(), reqTemplate);
        }
    }

    public Message<?> getMsgTemplate(int msgId) {
        Message<?> msgTemplate = msgId2Template.get(msgId);
        if (msgTemplate == null) {
            log.error("未注册该消息; msgId:{} {}", msgId, Util.getStackTrace());
        }
        return msgTemplate;
    }

    @SuppressWarnings("unchecked")
    public Class<? extends Message<?>> getMsgClass(int msgId) {
        Message<?> msgTemplate = getMsgTemplate(msgId);
        if (msgTemplate == null) {
            return null;
        }
        return (Class<? extends Message<?>>) msgTemplate.getClass();
    }
}
