package com.cream.helper.core.net.common.msg;

import com.cream.helper.core.net.common.constant.MsgMeta;
import com.cream.helper.core.net.common.msg.base.Message;
import com.cream.helper.core.net.proto.clazz.CommonProto;

public class ResLoginMsg extends Message<CommonProto.LoginRes> {
    @Override
    public MsgMeta getMsgMeta() {
        return MsgMeta.LoginRes;
    }

    @Override
    public Class<CommonProto.LoginRes> getDataClass() {
        return CommonProto.LoginRes.class;
    }
}
