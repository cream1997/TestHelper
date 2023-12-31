package com.cream.helper.core.net.msg;

import com.cream.helper.utils.NullUtil;

import java.util.Collections;
import java.util.List;

/**
 * 消息模板池子
 */
public class MessageTemplatePool {

    private final List<Message> allReqTemplate;

    private final List<Message> allResTemplate;

    public MessageTemplatePool(List<Message> allReqTemplate, List<Message> allResTemplate) {
        if (NullUtil.isAnyEmpty(allResTemplate, allResTemplate)) {
            throw new IllegalArgumentException("传入值为空");
        }
        this.allReqTemplate = Collections.unmodifiableList(allReqTemplate);
        this.allResTemplate = Collections.unmodifiableList(allResTemplate);
    }


    public List<Message> getAllReqTemplate() {
        return allReqTemplate;
    }
    
    public List<Message> getAllResTemplate() {
        return allResTemplate;
    }
}
