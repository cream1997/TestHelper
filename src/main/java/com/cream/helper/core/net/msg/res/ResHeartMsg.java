package com.cream.helper.core.net.msg.res;

import com.cream.helper.core.net.common.constant.MsgMeta;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.proto.clazz.CommonProto;

import java.util.function.Supplier;

public class ResHeartMsg extends Message<CommonProto.Heart> {
    public ResHeartMsg(Supplier<CommonProto.Heart> dataBuilder) {
        super(dataBuilder);
    }

    @Override
    public MsgMeta getMsgMeta() {
        return MsgMeta.HeartRes;
    }

    @Override
    public Class<CommonProto.Heart> getDataClass() {
        return CommonProto.Heart.class;
    }
}
