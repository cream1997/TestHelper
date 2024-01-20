package com.cream.helper.core.net.msg;

import com.cream.helper.core.net.msg.constant.MsgMeta;
import com.cream.helper.core.net.msg.constant.MsgType;
import com.google.protobuf.GeneratedMessageV3;
import lombok.Getter;

public abstract class Message<T extends GeneratedMessageV3> {
    @Getter
    private final int msgId;
    @Getter
    private final MsgType type;
    /**
     * 消息体（内容）
     */
    private T data;

    private Message(int msgId, MsgType type) {
        this.msgId = msgId;
        this.type = type;
    }

    public Message(MsgMeta msgMeta) {
        this(msgMeta.msgId, msgMeta.msgType);
    }

}
