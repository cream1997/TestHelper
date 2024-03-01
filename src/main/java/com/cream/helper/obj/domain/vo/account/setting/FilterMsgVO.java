package com.cream.helper.obj.domain.vo.account.setting;

import lombok.Data;

@Data
public class FilterMsgVO {
    private final int msgId;
    private final String msgName;
    private final boolean filter;

    public FilterMsgVO(int msgId, String msgName, boolean filter) {
        this.msgId = msgId;
        this.msgName = msgName;
        this.filter = filter;
    }
}
