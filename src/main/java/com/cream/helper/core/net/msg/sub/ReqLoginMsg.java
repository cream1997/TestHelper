package com.cream.helper.core.net.msg.sub;

import com.cream.helper.core.net.msg.Message;
import com.cream.helper.core.net.msg.constant.MsgMeta;
import com.cream.helper.core.net.proto.clazz.CommonProto;

public class ReqLoginMsg extends Message<CommonProto.LoginReq> {
    public ReqLoginMsg() {
        super(MsgMeta.LoginReq);
    }
}
