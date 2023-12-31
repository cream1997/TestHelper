package com.cream.helper.core.net.bo;

import com.cream.helper.core.net.GameClient;
import com.cream.helper.utils.Times;
import lombok.Getter;
import lombok.Setter;

@Getter
public class RoleSession {

    private final long rid;

    private final long userId;

    @Setter
    private long lastHeartTime;

    private final GameClient gameClient;

    public RoleSession(long rid, long userId, GameClient gameClient) {
        this.rid = rid;
        this.userId = userId;
        this.gameClient = gameClient;
        this.lastHeartTime = Times.now();
    }
}
