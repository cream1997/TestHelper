package com.cream.helper.core.net.common.codec;

import com.cream.helper.core.net.msg.base.Message;
import com.google.protobuf.GeneratedMessageV3;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsgEncoder extends MessageToByteEncoder<Message<?>> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message<?> msg, ByteBuf out) throws Exception {
        // 写入消息id
        int msgId = msg.getMsgId();
        if (msgId == 169894448) {
            log.error("");
        }
        out.writeInt(msgId);
        int length = 0;
        // 写入消息体
        GeneratedMessageV3 data = msg.getData();
        if (data == null) {
            out.writeInt(length);
        } else {
            byte[] byteArray = data.toByteArray();
            length = byteArray.length;
            out.writeInt(length);
            out.writeBytes(byteArray);
        }
    }
}
