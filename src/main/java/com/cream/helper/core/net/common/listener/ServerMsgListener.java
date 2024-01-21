package com.cream.helper.core.net.common.listener;

import com.cream.helper.core.net.common.listener.base.MsgListener;
import com.cream.helper.core.net.handler.base.MsgHandler;
import com.cream.helper.core.net.handler.sub.ReqLoginMsgHandler;
import com.cream.helper.core.net.msg.base.Message;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ServerMsgListener extends MsgListener {

    private final Map<Integer, MsgHandler<?>> allServerMsgHandler = new ConcurrentHashMap<>();

    public ServerMsgListener() {
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
    private void registerHandler() {
        addHandler(new ReqLoginMsgHandler());
    }

    private void addHandler(MsgHandler<?> msgHandler) {
        try {
            int msgId = getMsgId(msgHandler);
            allServerMsgHandler.put(msgId, msgHandler);
        } catch (Exception e) {
            log.error("注册处理器发生异常", e);
        }
    }

    @SuppressWarnings("unchecked")
    private int getMsgId(MsgHandler<?> msgHandler) throws Exception {
        ParameterizedType parameterizedType = (ParameterizedType) msgHandler.getClass().getGenericSuperclass();
        Class<Message<?>> msgClass = (Class<Message<?>>) parameterizedType.getActualTypeArguments()[0];
        try {
            Constructor<Message<?>> constructor = (Constructor<Message<?>>) msgClass.getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            Object[] nullParams = new Object[constructor.getParameterCount()];
            return constructor.newInstance(nullParams).getMsgId();
        } catch (Exception e) {
            log.error("获取msgId异常 msgHandler:{}", msgHandler.getClass());
            throw e;
        }
    }


}
