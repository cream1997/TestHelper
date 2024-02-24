package com.cream.helper.core.net.msg.res.test;

import com.cream.helper.core.net.common.constant.MsgMeta;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.proto.clazz.TestProto;

import java.util.function.Supplier;

public class ResTestOneOneMsg extends Message<TestProto.Test1> {
    public ResTestOneOneMsg(Supplier<TestProto.Test1> dataBuilder) {
        super(dataBuilder);
    }

    @Override
    public MsgMeta getMsgMeta() {
        return MsgMeta.TestMsg2;
    }

    @Override
    public Class<TestProto.Test1> getDataClass() {
        return TestProto.Test1.class;
    }
}
