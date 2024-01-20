package com.cream.helper.core.net.common.msg.base;

import com.google.protobuf.GeneratedMessageV3;

/**
 * 没有消息体的消息
 */
public abstract class MessageWithoutData extends Message<GeneratedMessageV3> {


    public MessageWithoutData() {
    }

    public MessageWithoutData(GeneratedMessageV3 data) {
        throw new UnsupportedOperationException();
    }

    public MessageWithoutData(byte[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<GeneratedMessageV3> getDataClass() {
        return null;
    }
}
