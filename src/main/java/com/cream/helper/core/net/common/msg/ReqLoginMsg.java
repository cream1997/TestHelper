package com.cream.helper.core.net.common.msg;

import com.cream.helper.core.net.common.constant.MsgMeta;
import com.cream.helper.core.net.common.msg.base.Message;
import com.cream.helper.core.net.proto.clazz.CommonProto;

public class ReqLoginMsg extends Message<CommonProto.LoginReq> {

    @Override
    public MsgMeta getMsgMeta() {
        return MsgMeta.LoginReq;
    }

    @Override
    public Class<CommonProto.LoginReq> getDataClass() {
        return CommonProto.LoginReq.class;
    }
}
