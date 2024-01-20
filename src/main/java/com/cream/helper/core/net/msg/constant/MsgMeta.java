package com.cream.helper.core.net.msg.constant;


import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public enum MsgMeta {

    LoginReq(1, MsgType.Req),
    LoginRes(2, MsgType.Res);


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
                System.exit(1);
            }
            if (msgIds.contains(msgId)) {
                log.error("消息id重复 msgId:{}", msgId);
                System.exit(1);
            }
            msgIds.add(msgId);
        }
    }
}
