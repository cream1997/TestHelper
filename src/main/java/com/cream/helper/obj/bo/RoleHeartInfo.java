package com.cream.helper.obj.bo;

import lombok.Getter;

@Getter
public class RoleHeartInfo {
    private final long userId;
    private final long rid;
    private final long serverTime;

    public RoleHeartInfo(long userId, long rid, long serverTime) {
        this.userId = userId;
        this.rid = rid;
        this.serverTime = serverTime;
    }
}
