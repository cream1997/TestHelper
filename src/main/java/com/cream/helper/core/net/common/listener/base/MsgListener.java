package com.cream.helper.core.net.common.listener.base;

import com.cream.helper.core.net.msg.base.Message;
import io.netty.channel.ChannelHandlerContext;

public abstract class MsgListener {
    public abstract void receiveMsg(ChannelHandlerContext ctx, Message<?> msg);
}
