package com.cream.helper.core.net.common.msg.listener;

import com.cream.helper.core.net.common.msg.base.Message;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

@Component
public class ClientMsgListener extends MsgListener {

    @Override
    public void readMsg(ChannelHandlerContext ctx, Message<?> msg) {

    }
}
