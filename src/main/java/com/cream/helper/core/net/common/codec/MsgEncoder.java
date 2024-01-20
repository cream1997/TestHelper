package com.cream.helper.core.net.common.codec;

import com.cream.helper.core.net.common.msg.base.Message;
import com.google.protobuf.GeneratedMessageV3;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.stereotype.Component;

@Component
public class MsgEncoder extends MessageToByteEncoder<Message<? extends GeneratedMessageV3>> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message<? extends GeneratedMessageV3> msg, ByteBuf out) throws Exception {
        // 写入消息id
        out.writeInt(msg.getMsgId());
        // 写入消息体
        GeneratedMessageV3 data = msg.getData();
        if (data != null) {
            byte[] byteArray = data.toByteArray();
            out.writeBytes(byteArray);
        }
    }
}
