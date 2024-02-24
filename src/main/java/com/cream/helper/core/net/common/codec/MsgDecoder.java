package com.cream.helper.core.net.common.codec;

import com.cream.helper.core.net.common.MsgTemplatePool;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.utils.MsgClassUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsgDecoder extends LengthFieldBasedFrameDecoder {

    private final MsgTemplatePool msgTemplatePool;

    public MsgDecoder(MsgTemplatePool msgTemplatePool) {
        super(65536, 4, 4);
        this.msgTemplatePool = msgTemplatePool;
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf decode = (ByteBuf) super.decode(ctx, in);
        if (decode == null) {
            return null;
        }
        int msgId = decode.readInt();
        Class<? extends Message<?>> msgClass = msgTemplatePool.getMsgClass(msgId);
        if (msgClass == null) {
            return null;
        }
        Message<?> message;
        int length = decode.readInt();
        if (length > 0) {
            byte[] bytes = new byte[length];
            decode.readBytes(bytes);
            message = buildMsgWithData(msgClass, bytes);
        } else {
            message = msgClass.newInstance();
        }
        return message;
    }

    private Message<?> buildMsgWithData(Class<? extends Message<?>> msgClass, byte[] bytes) {
        Message<?> message = MsgClassUtil.newMsgInstance(msgClass);
        message.setDataFromBytes(bytes);
        return message;
    }
}
