package com.cream.helper.core.net.common;

import com.cream.helper.core.net.common.constant.MsgType;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.common.listener.ClientMsgListener;
import com.cream.helper.core.net.common.listener.ServerMsgListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MockServer不考虑线程问题，所有任务到一个线程中执行
 */
@Slf4j
@Component
public class GlobalMsgHandler extends SimpleChannelInboundHandler<Message<?>> {


    private final ServerMsgListener serverMsgListener;
    private final ClientMsgListener clientMsgListener;

    @Autowired
    public GlobalMsgHandler(ServerMsgListener serverMsgListener, ClientMsgListener clientMsgListener) {
        this.serverMsgListener = serverMsgListener;
        this.clientMsgListener = clientMsgListener;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message<?> msg) {
        MsgType type = msg.getType();
        if (type == MsgType.Req) {
            serverMsgListener.receiveMsg(ctx, msg);
        } else if (type == MsgType.Res) {
            clientMsgListener.receiveMsg(ctx, msg);
        } else {
            log.error("消息类型错误 type:{}", type);
        }
    }
}
