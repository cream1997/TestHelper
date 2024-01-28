package com.cream.helper.core.net.handler.base;

import com.cream.helper.core.net.msg.base.Message;

public abstract class MsgHandler<T extends Message<?>> {
    public abstract Message<?> handle(T msg);

    @SuppressWarnings("unchecked")
    public final Message<?> handle0(Message<?> msg) {
        return handle((T) msg);
    }
}
