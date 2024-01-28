package com.cream.helper.core.net.msg.req;

import com.cream.helper.core.net.common.constant.MsgMeta;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.proto.clazz.CommonProto;

import java.util.function.Supplier;

public class ReqHeartMsg extends Message<CommonProto.Heart> {
    public ReqHeartMsg(Supplier<CommonProto.Heart> dataBuilder) {
        super(dataBuilder);
    }

    @Override
    public MsgMeta getMsgMeta() {
        return MsgMeta.HeartReq;
    }

    @Override
    public Class<CommonProto.Heart> getDataClass() {
        return CommonProto.Heart.class;
    }
}
