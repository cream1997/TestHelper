package com.cream.helper.core.net;

import lombok.Getter;

public class Message {
    @Getter
    private final int msgId;
    @Getter
    private final Type type;
    /**
     * 消息体（内容）
     */
    private Object data;

    public Message(int msgId, Type type) {
        this.msgId = msgId;
        this.type = type;
    }

    public enum Type {
        Request,
        Response
    }
}
