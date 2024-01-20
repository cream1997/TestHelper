package com.cream.helper.core.net.msg.sub;

import com.cream.helper.core.net.msg.Message;
import com.cream.helper.core.net.msg.constant.MsgMeta;
import com.cream.helper.core.net.proto.clazz.CommonProto;

public class ResLoginMsg extends Message<CommonProto.LoginRes> {
    public ResLoginMsg() {
        super(MsgMeta.LoginRes);
    }
}
