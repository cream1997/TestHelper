package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.core.net.UserSessionManager;
import com.cream.helper.core.net.bo.UserSession;
import com.cream.helper.core.net.client.GameClient;
import com.cream.helper.core.net.common.MsgTemplatePool;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.obj.Ret;
import com.cream.helper.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MockComponent
public class MockMessageService implements IMessageService {

    private final UserSessionManager userSessionManager;

    private final MsgTemplatePool msgTemplatePool;

    @Autowired
    public MockMessageService(UserSessionManager userSessionManager,
                              MsgTemplatePool msgTemplatePool) {
        this.userSessionManager = userSessionManager;
        this.msgTemplatePool = msgTemplatePool;
    }

    @Override
    public Ret<String> sendRequest(long rid, Message message) {
        if (userSessionManager.isOffLine(rid)) {
            return Ret.err("角色不在线");
        }
        UserSession userSession = userSessionManager.getSession(rid);
        GameClient gameClient = userSession.getGameClient();
        return Ret.ok("发送成功");
    }

    @Override
    public Ret<List<Message<?>>> fetchAllReqTemplate() {
        return Ret.ok(msgTemplatePool.getAllReqTemplate());
    }

    @Override
    public Ret<List<Message<?>>> fetchAllResTemplate() {
        return Ret.ok(msgTemplatePool.getAllResTemplate());
    }

    @Override
    public Ret<List<Message<?>>> fetchAllResMsg(long rid) {
        if (userSessionManager.isOffLine(rid)) {
            return Ret.err("角色不在线");
        }
        // todo
        return Ret.ok(null);
    }
}
