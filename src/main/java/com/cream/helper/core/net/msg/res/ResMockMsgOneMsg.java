package com.cream.helper.core.net.msg.res;

import com.cream.helper.core.net.common.constant.MsgMeta;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.proto.clazz.CommonProto;

import java.util.function.Supplier;

public class ResMockMsgOneMsg extends Message<CommonProto.MockMsgOne> {

    public ResMockMsgOneMsg(Supplier<CommonProto.MockMsgOne> dataBuilder) {
        super(dataBuilder);
    }

    @Override
    public MsgMeta getMsgMeta() {
        return MsgMeta.MockMsgOne;
    }

    @Override
    public Class<CommonProto.MockMsgOne> getDataClass() {
        return CommonProto.MockMsgOne.class;
    }
}
