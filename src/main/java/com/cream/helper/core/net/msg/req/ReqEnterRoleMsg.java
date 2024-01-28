package com.cream.helper.core.net.msg.req;

import com.cream.helper.core.net.common.constant.MsgMeta;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.proto.clazz.CommonProto;

import java.util.function.Supplier;

public class ReqEnterRoleMsg extends Message<CommonProto.EnterRoleReq> {
    public ReqEnterRoleMsg(Supplier<CommonProto.EnterRoleReq> dataBuilder) {
        super(dataBuilder);
    }

    @Override
    public MsgMeta getMsgMeta() {
        return MsgMeta.EnterRoleReq;
    }

    @Override
    public Class<CommonProto.EnterRoleReq> getDataClass() {
        return CommonProto.EnterRoleReq.class;
    }
}
