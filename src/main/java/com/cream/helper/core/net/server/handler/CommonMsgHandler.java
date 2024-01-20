package com.cream.helper.core.net.server.handler;

import com.cream.helper.core.net.common.msg.base.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

@Component
public class CommonMsgHandler extends SimpleChannelInboundHandler<Message<?>> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message<?> msg) {
    }
}
