package com.cream.helper.core.net.msg.res;

import com.cream.helper.core.net.common.constant.MsgMeta;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.proto.clazz.CommonProto;

import java.util.function.Supplier;

public class ResEnterRoleMsg extends Message<CommonProto.EnterRoleRes> {
    public ResEnterRoleMsg(Supplier<CommonProto.EnterRoleRes> dataBuilder) {
        super(dataBuilder);
    }

    @Override
    public MsgMeta getMsgMeta() {
        return MsgMeta.EnterRoleRes;
    }

    @Override
    public Class<CommonProto.EnterRoleRes> getDataClass() {
        return CommonProto.EnterRoleRes.class;
    }
}
