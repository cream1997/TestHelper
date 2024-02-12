package com.cream.helper.core.net.common.codec;

import com.cream.helper.core.net.common.MsgTemplatePool;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.utils.MsgClassUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Slf4j
public class MsgDecoder extends ByteToMessageDecoder {

    private final MsgTemplatePool msgTemplatePool;

    public MsgDecoder(MsgTemplatePool msgTemplatePool) {
        this.msgTemplatePool = msgTemplatePool;
    }


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 8) {
            return; // 字节流不足，等待更多数据到达
        }
        int msgId = in.readInt();
        Class<? extends Message<?>> msgClass = msgTemplatePool.getMsgClass(msgId);
        if (msgClass == null) {
            return;
        }
        Message<?> message;
        int length = in.readInt();
        if (length > 0) {
            if (in.readableBytes() < length) {
                in.resetReaderIndex(); // 重置读取位置
                return; // 字节流不足，等待更多数据到达
            }
            byte[] bytes = new byte[length];
            in.readBytes(bytes);
            message = buildMsgWithData(msgClass, bytes);
        } else {
            message = msgClass.newInstance();
        }
        out.add(message);
    }

    private Message<?> buildMsgWithData(Class<? extends Message<?>> msgClass, byte[] bytes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Message<?> message = MsgClassUtil.newMsgInstance(msgClass);
        message.setDataFromBytes(bytes);
        return message;
    }
}
