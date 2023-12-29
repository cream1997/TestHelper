package com.cream.helper.obj.entity.cmd;

import java.util.Map;

public class Command {

    private final long id;

    private final String cmdName;

    private final int reqMsgId;

    private Map<String, RightValueExpression> assignExpression;


    public Command(long id, String cmdName, int reqMsgId) {
        this.id = id;
        this.cmdName = cmdName;
        this.reqMsgId = reqMsgId;
    }
}
