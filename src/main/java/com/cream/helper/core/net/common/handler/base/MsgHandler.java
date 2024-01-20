package com.cream.helper.core.net.common.handler.base;

import com.cream.helper.core.net.common.msg.base.Message;

public abstract class MsgHandler<T extends Message<?>> {
    public abstract Message<?> handle(T message);

    @SuppressWarnings("unchecked")
    public final Message<?> handle0(Message<?> message) {
        return handle((T) message);
    }
}
