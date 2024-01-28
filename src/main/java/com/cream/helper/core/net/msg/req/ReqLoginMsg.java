package com.cream.helper.core.net.msg.req;

import com.cream.helper.core.net.common.constant.MsgMeta;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.proto.clazz.CommonProto;

import java.util.function.Supplier;

public class ReqLoginMsg extends Message<CommonProto.LoginReq> {

    public ReqLoginMsg(Supplier<CommonProto.LoginReq> dataBuilder) {
        super(dataBuilder);
    }

    @Override
    public MsgMeta getMsgMeta() {
        return MsgMeta.LoginReq;
    }

    @Override
    public Class<CommonProto.LoginReq> getDataClass() {
        return CommonProto.LoginReq.class;
    }
}
