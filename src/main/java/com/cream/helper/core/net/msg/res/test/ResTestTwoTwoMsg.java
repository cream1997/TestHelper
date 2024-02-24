package com.cream.helper.core.net.msg.res.test;

import com.cream.helper.core.net.common.constant.MsgMeta;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.proto.clazz.TestProto;

import java.util.function.Supplier;

public class ResTestTwoTwoMsg extends Message<TestProto.Test2> {

    public ResTestTwoTwoMsg(Supplier<TestProto.Test2> dataBuilder) {
        super(dataBuilder);
    }

    @Override
    public MsgMeta getMsgMeta() {
        return MsgMeta.TestMsg2;
    }

    @Override
    public Class<TestProto.Test2> getDataClass() {
        return TestProto.Test2.class;
    }
}
