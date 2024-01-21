package com.cream.helper.core.net.common.msg.handler.sub;

import com.cream.helper.core.net.common.msg.ReqLoginMsg;
import com.cream.helper.core.net.common.msg.ResLoginMsg;
import com.cream.helper.core.net.common.msg.base.Message;
import com.cream.helper.core.net.common.msg.handler.base.MsgHandler;

public class ReqLoginMsgHandler extends MsgHandler<ReqLoginMsg> {
    @Override
    public Message<?> handle(ReqLoginMsg message) {
        ResLoginMsg resLoginMsg = new ResLoginMsg();
        // todo
        return resLoginMsg;
    }
}
