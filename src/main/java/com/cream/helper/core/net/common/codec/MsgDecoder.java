package com.cream.helper.core.net.common.codec;

import com.cream.helper.core.net.common.MsgTemplatePool;
import com.cream.helper.core.net.msg.base.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.util.List;

@Component
public class MsgDecoder extends ByteToMessageDecoder {

    private final MsgTemplatePool msgTemplatePool;

    @Autowired
    public MsgDecoder(MsgTemplatePool msgTemplatePool) {
        this.msgTemplatePool = msgTemplatePool;
    }


    @SuppressWarnings("rawtypes")
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int msgId = in.readInt();
        Class<? extends Message> msgClass = msgTemplatePool.getMsgClass(msgId);
        Message message;
        int leftLength = in.readableBytes();
        if (leftLength > 0) {
            byte[] bytes = new byte[leftLength];
            in.readBytes(bytes);
            Constructor<? extends Message> constructor = msgClass.getDeclaredConstructor(byte[].class);
            constructor.setAccessible(true);
            message = constructor.newInstance(bytes);
        } else {
            message = msgClass.newInstance();
        }
        out.add(message);
    }
}
