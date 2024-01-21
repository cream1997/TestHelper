package com.cream.helper.core.net.msg.base;

import com.google.protobuf.GeneratedMessageV3;

/**
 * 没有消息体的消息
 */
public abstract class MessageWithoutData extends Message<GeneratedMessageV3> {


    public MessageWithoutData() {
        super(null);
    }

    @Override
    public Class<GeneratedMessageV3> getDataClass() {
        return null;
    }
}
