package com.cream.helper.core.net.common.constant;

public enum MsgType {
    Req(1),
    Res(2);

    public final int value;

    MsgType(int value) {
        this.value = value;
    }
}
