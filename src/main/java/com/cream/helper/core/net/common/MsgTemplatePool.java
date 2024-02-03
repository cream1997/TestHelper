package com.cream.helper.core.net.common;

import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.utils.NullUtil;
import com.cream.helper.utils.Util;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

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

    private final Map<String, Message<?>> name2ReqMsg;

    public MsgTemplatePool(List<Message<?>> allReqTemplate, List<Message<?>> allResTemplate) {
        if (NullUtil.isAnyEmpty(allResTemplate, allResTemplate)) {
            throw new IllegalArgumentException("传入值为空");
        }
        this.allReqTemplate = Collections.unmodifiableList(allReqTemplate);
        this.allResTemplate = Collections.unmodifiableList(allResTemplate);
        this.msgId2Template = Collections.unmodifiableMap(initMsgId2Template());
        this.name2ReqMsg = Collections.unmodifiableMap(initName2ReqMsg());
    }

    private Map<String, Message<?>> initName2ReqMsg() {
        Map<String, Message<?>> result = new HashMap<>();
        for (Message<?> message : allReqTemplate) {
            String simpleName = message.getClass().getSimpleName();
            NullUtil.assertNoNull(simpleName);
            if (result.containsKey(simpleName)) {
                log.error("重复的消息名称:{}", simpleName);
            }
            result.put(message.getClass().getSimpleName(), message);
        }
        return result;
    }

    private Map<Integer, Message<?>> initMsgId2Template() {
        HashMap<Integer, Message<?>> modifiableMap = new HashMap<>();
        putInMsgTemplate(modifiableMap, allReqTemplate);
        putInMsgTemplate(modifiableMap, allResTemplate);
        return modifiableMap;
    }

    private void putInMsgTemplate(Map<Integer, Message<?>> modifiableMap, List<Message<?>> msgTemplate) {
        for (Message<?> reqTemplate : msgTemplate) {
            modifiableMap.put(reqTemplate.getMsgId(), reqTemplate);
        }
    }

    public Message<?> getMsgTemplate(int msgId) {
        Message<?> msgTemplate = msgId2Template.get(msgId);
        if (msgTemplate == null) {
            log.error("未注册该消息; msgId:{} {}", msgId, Util.getSimpleStackTrace());
        }
        return msgTemplate;
    }

    public List<Message<?>> searchMsgTemplates(String msgLikeName, int maxResSize) {
        if (NullUtil.isBlank(msgLikeName)) {
            return Collections.emptyList();
        }
        List<Message<?>> result = new ArrayList<>();
        for (String msgName : name2ReqMsg.keySet()) {
            if (result.size() >= maxResSize) {
                break;
            }
            if (msgName.toLowerCase().contains(msgLikeName.toLowerCase())) {
                result.add(name2ReqMsg.get(msgName));
            }
        }
        return result;
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
