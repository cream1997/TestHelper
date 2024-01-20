package com.cream.helper.core.net.common;

import com.cream.helper.core.net.common.msg.base.Message;
import com.cream.helper.utils.NullUtil;
import com.google.protobuf.GeneratedMessageV3;

import java.util.Collections;
import java.util.List;

/**
 * 消息模板池子
 */
public class MessageTemplatePool {

    private final List<Message<? extends GeneratedMessageV3>> allReqTemplate;

    private final List<Message<? extends GeneratedMessageV3>> allResTemplate;

    public MessageTemplatePool(List<Message<? extends GeneratedMessageV3>> allReqTemplate, List<Message<? extends GeneratedMessageV3>> allResTemplate) {
        if (NullUtil.isAnyEmpty(allResTemplate, allResTemplate)) {
            throw new IllegalArgumentException("传入值为空");
        }
        this.allReqTemplate = Collections.unmodifiableList(allReqTemplate);
        this.allResTemplate = Collections.unmodifiableList(allResTemplate);
    }


    public List<Message<? extends GeneratedMessageV3>> getAllReqTemplate() {
        return allReqTemplate;
    }

    public List<Message<? extends GeneratedMessageV3>> getAllResTemplate() {
        return allResTemplate;
    }
}
