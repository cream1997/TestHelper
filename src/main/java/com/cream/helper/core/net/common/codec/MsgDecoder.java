package com.cream.helper.core.net.common.codec;

import com.cream.helper.core.net.common.MsgTemplatePool;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.utils.MsgReflectUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MsgDecoder extends ByteToMessageDecoder {

    private final MsgTemplatePool msgTemplatePool;

    public MsgDecoder(MsgTemplatePool msgTemplatePool) {
        this.msgTemplatePool = msgTemplatePool;
    }


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int msgId = in.readInt();
        Class<? extends Message<?>> msgClass = msgTemplatePool.getMsgClass(msgId);
        Message<?> message;
        int leftLength = in.readableBytes();
        if (leftLength > 0) {
            byte[] bytes = new byte[leftLength];
            in.readBytes(bytes);
            message = buildMsgWithData(msgClass, bytes);
        } else {
            message = msgClass.newInstance();
        }
        out.add(message);
    }

    private Message<?> buildMsgWithData(Class<? extends Message<?>> msgClass, byte[] bytes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Message<?> message = MsgReflectUtil.newMsgInstance(msgClass);
        Method setDataMethod = message.getClass().getSuperclass().getDeclaredMethod("setData", byte[].class);
        setDataMethod.setAccessible(true);
        setDataMethod.invoke(message, bytes);
        return message;
    }
}
