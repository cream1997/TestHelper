package com.cream.helper.core.net.common;

import com.cream.helper.config.AppSetup;
import com.cream.helper.constant.GamePlatform;
import com.cream.helper.core.net.common.codec.MsgDecoder;
import com.cream.helper.core.net.common.codec.MsgEncoder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GameNetSetup {
    private final GamePlatform gamePlatform;
    private final MsgEncoder msgEncoder;
    private final MsgDecoder msgDecoder;
    private final GlobalMsgHandler globalMsgHandler;

    @Autowired
    public GameNetSetup(AppSetup appSetup,
                        MsgEncoder msgEncoder,
                        MsgDecoder msgDecoder,
                        GlobalMsgHandler globalMsgHandler) {
        this.gamePlatform = appSetup.getGamePlatform();
        this.msgEncoder = msgEncoder;
        this.msgDecoder = msgDecoder;
        this.globalMsgHandler = globalMsgHandler;
    }
}
