package com.cream.helper.core.net.bo;

import com.cream.helper.core.net.client.GameClient;
import com.cream.helper.utils.Times;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserSession {

    private final long userId;
    private long rid;

    @Setter
    private long lastHeartTime;

    private final GameClient gameClient;

    public UserSession(long userId, GameClient gameClient) {
        this.userId = userId;
        this.gameClient = gameClient;
        this.lastHeartTime = Times.now();
    }
}
