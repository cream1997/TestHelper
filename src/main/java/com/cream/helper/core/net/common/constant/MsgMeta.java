package com.cream.helper.core.net.common.constant;


import com.cream.helper.utils.Util;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public enum MsgMeta {

    LoginReq(1, MsgType.Req),
    LoginRes(2, MsgType.Res),
    EnterRoleReq(3, MsgType.Req),
    EnterRoleRes(4, MsgType.Res),
    HeartReq(5, MsgType.Req),
    HeartRes(6, MsgType.Res),
    MockMsgOne(7, MsgType.Res),

    TestMsg1(99, MsgType.Res),
    TestMsg2(100, MsgType.Res),
    TestMsg3(101, MsgType.Res),
    ;


    public final int msgId;
    public final MsgType msgType;

    MsgMeta(int msgId, MsgType msgType) {
        this.msgId = msgId;
        this.msgType = msgType;
    }

    static {
        check();
    }

    private static void check() {
        Set<Integer> msgIds = new HashSet<>();
        for (MsgMeta msgMeta : MsgMeta.values()) {
            int msgId = msgMeta.msgId;
            if (msgMeta.msgType == null) {
                log.error("消息类型为空 msgId:{}", msgId);
                Util.errExit();
            }
            if (msgIds.contains(msgId)) {
                log.error("消息id重复 msgId:{}", msgId);
                Util.errExit();
            }
            msgIds.add(msgId);
        }
    }
}
