package com.cream.helper.core.net.msg.res.test;

import com.cream.helper.core.net.common.constant.MsgMeta;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.proto.clazz.TestProto;

import java.util.function.Supplier;

public class ResTestThreeMsg extends Message<TestProto.Test3> {
    public ResTestThreeMsg(Supplier<TestProto.Test3> dataBuilder) {
        super(dataBuilder);
    }

    @Override
    public MsgMeta getMsgMeta() {
        return MsgMeta.TestMsg3;
    }

    @Override
    public Class<TestProto.Test3> getDataClass() {
        return TestProto.Test3.class;
    }
}
