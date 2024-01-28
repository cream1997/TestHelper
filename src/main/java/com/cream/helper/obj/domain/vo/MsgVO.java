package com.cream.helper.obj.domain.vo;

import com.cream.helper.core.net.msg.base.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MsgVO {
    private final int no;
    private final int msgId;
    private final String msgName;
    private final int type;
    private final long sendTime;
    private final long receiveTime;
    private final Object data;

    public MsgVO(Message<?> msg) {
        this.no = msg.getSerialNum();
        this.msgId = msg.getMsgId();
        this.msgName = msg.getClass().getSimpleName();
        this.type = msg.getType().value;
        this.sendTime = msg.getSendTime();
        this.receiveTime = msg.getReceiveTime();
        this.data = msg.getData();
    }
}
