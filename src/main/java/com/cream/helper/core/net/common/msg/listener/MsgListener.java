package com.cream.helper.core.net.common.msg.listener;

import com.cream.helper.core.net.common.msg.base.Message;
import io.netty.channel.ChannelHandlerContext;

public abstract class MsgListener {
    public abstract void readMsg(ChannelHandlerContext ctx, Message<?> msg);
}
