package com.cream.helper.core.net.common.handler.server;

import com.cream.helper.core.net.common.handler.base.MsgHandler;
import com.cream.helper.core.net.common.msg.ReqLoginMsg;
import com.cream.helper.core.net.common.msg.ResLoginMsg;
import com.cream.helper.core.net.common.msg.base.Message;

public class ReqLoginMsgHandler extends MsgHandler<ReqLoginMsg> {
    @Override
    public Message<?> handle(ReqLoginMsg message) {
        ResLoginMsg resLoginMsg = new ResLoginMsg();
        // todo
        return resLoginMsg;
    }
}
