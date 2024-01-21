package com.cream.helper.core.net.common.listener;

import com.cream.helper.core.net.client.GameClient;
import com.cream.helper.core.net.common.listener.base.MsgListener;
import com.cream.helper.core.net.msg.base.Message;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientMsgListener extends MsgListener {

    @Override
    public void receiveMsg(ChannelHandlerContext ctx, Message<?> msg) {
        // 客户端收到消息存入消息池
        GameClient gameClient = ctx.channel().attr(GameClient.ClientRefKey).get();
        if (gameClient == null) {
            log.error("客户端收到消息后，找不到client引用");
            return;
        }
        gameClient.receiveMsg(msg);
    }
}
