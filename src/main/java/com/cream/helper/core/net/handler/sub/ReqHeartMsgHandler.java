package com.cream.helper.core.net.handler.sub;

import com.cream.helper.core.net.handler.base.MsgHandler;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.msg.req.ReqHeartMsg;
import com.cream.helper.core.net.proto.clazz.CommonProto;
import com.cream.helper.utils.Times;
import org.springframework.stereotype.Component;

@Component
public class ReqHeartMsgHandler extends MsgHandler<ReqHeartMsg> {
    @Override
    public Message<?> handle(ReqHeartMsg msg) {
        return new ReqHeartMsg(() -> CommonProto.Heart.newBuilder()
                .setTime(Times.now())
                .build());
    }
}
