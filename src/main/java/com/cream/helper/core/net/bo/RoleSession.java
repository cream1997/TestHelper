package com.cream.helper.core.net.bo;

import com.cream.helper.utils.Times;
import lombok.Getter;
import lombok.Setter;

@Getter
public class RoleSession {

    private final long rid;

    private final long userId;

    @Setter
    private long lastHeartTime;

    public RoleSession(long rid, long userId) {
        this.rid = rid;
        this.userId = userId;
        this.lastHeartTime = Times.now();
    }
}
