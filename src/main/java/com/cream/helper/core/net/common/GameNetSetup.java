package com.cream.helper.core.net.common;

import com.cream.helper.config.AppSetup;
import com.cream.helper.constant.GamePlatform;
import com.cream.helper.core.net.common.codec.MsgDecoder;
import com.cream.helper.core.net.common.codec.MsgEncoder;
import com.cream.helper.core.net.common.listener.ClientMsgListener;
import com.cream.helper.core.net.common.listener.ServerMsgListener;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GameNetSetup {
    @Getter
    private final GamePlatform gamePlatform;
    private final ServerMsgListener serverMsgListener;
    private final ClientMsgListener clientMsgListener;
    private final MsgTemplatePool msgTemplatePool;

    @Autowired
    public GameNetSetup(AppSetup appSetup,
                        ServerMsgListener serverMsgListener,
                        ClientMsgListener clientMsgListener,
                        MsgTemplatePool msgTemplatePool) {
        this.gamePlatform = appSetup.getGamePlatform();
        this.serverMsgListener = serverMsgListener;
        this.clientMsgListener = clientMsgListener;
        this.msgTemplatePool = msgTemplatePool;
    }


    /**
     * 消息编码器不能是共享的，所以不能以单例的方式注入容器
     */
    public MsgEncoder getMsgEncoder() {
        return new MsgEncoder();
    }

    /**
     * 消息解码器器不能是共享的，所以不能以单例的方式注入容器
     */
    public MsgDecoder getMsgDecoder() {
        return new MsgDecoder(msgTemplatePool);
    }

    /**
     * 全局消息处理器不能是共享的，所以不能以单例的方式注入容器
     */
    public GlobalMsgHandler getGlobalMsgHandler() {
        return new GlobalMsgHandler(serverMsgListener, clientMsgListener);
    }
}
