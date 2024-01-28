package com.cream.helper.core.net.common.listener;

import com.cream.helper.core.net.common.listener.base.MsgListener;
import com.cream.helper.core.net.handler.base.MsgHandler;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.utils.MsgReflectUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ServerMsgListener extends MsgListener {

    private final Map<Integer, MsgHandler<?>> allServerMsgHandler = new ConcurrentHashMap<>();

    private final ApplicationContext appContext;

    public ServerMsgListener(ApplicationContext appContext) {
        this.appContext = appContext;
        registerHandler();
    }

    @Override
    public void receiveMsg(ChannelHandlerContext ctx, Message<?> msg) {
        MsgHandler<?> msgHandler = allServerMsgHandler.get(msg.getMsgId());
        if (msgHandler == null) {
            log.error("未定义消息处理器, msgId:{}", msg.getMsgId());
        } else {
            Message<?> resMsg = msgHandler.handle0(msg);
            ctx.channel().writeAndFlush(resMsg);
        }
    }

    /**
     * 目前不采用反射读取，简单一点
     */
    @SuppressWarnings("rawtypes")
    private void registerHandler() {
        Map<String, MsgHandler> allMsgHandler = appContext.getBeansOfType(MsgHandler.class);
        for (MsgHandler msgHandler : allMsgHandler.values()) {
            try {
                int msgId = MsgReflectUtil.getMsgId(msgHandler);
                allServerMsgHandler.put(msgId, msgHandler);
            } catch (Exception e) {
                log.error("注册处理器发生异常", e);
            }
        }
    }
}
