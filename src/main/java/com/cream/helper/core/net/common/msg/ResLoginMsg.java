package com.cream.helper.core.net.common.msg;

import com.cream.helper.core.net.common.constant.MsgMeta;
import com.cream.helper.core.net.common.msg.base.Message;
import com.cream.helper.core.net.proto.clazz.CommonProto;

public class ResLoginMsg extends Message<CommonProto.LoginRes> {
    public ResLoginMsg() {
        super(MsgMeta.LoginRes);
    }
}
