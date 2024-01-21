package com.cream.helper.core.net.msg;

import com.cream.helper.core.net.common.constant.MsgMeta;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.proto.clazz.CommonProto;

import java.util.function.Supplier;

public class ResLoginMsg extends Message<CommonProto.LoginRes> {
    public ResLoginMsg(Supplier<CommonProto.LoginRes> dataBuilder) {
        super(dataBuilder);
    }

    @Override
    public MsgMeta getMsgMeta() {
        return MsgMeta.LoginRes;
    }

    @Override
    public Class<CommonProto.LoginRes> getDataClass() {
        return CommonProto.LoginRes.class;
    }
}
