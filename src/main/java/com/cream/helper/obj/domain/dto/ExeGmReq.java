package com.cream.helper.obj.domain.dto;

import lombok.Getter;

@Getter
public class ExeGmReq {
    private final long rid;
    private final String gmCmd;

    private final String[] params;

    public ExeGmReq(long rid, String gmCmd, String[] params) {
        this.rid = rid;
        this.gmCmd = gmCmd;
        this.params = params;
    }
}
