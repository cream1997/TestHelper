package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.core.net.RoleSessionManager;
import com.cream.helper.core.net.bo.RoleSession;
import com.cream.helper.core.net.client.GameClient;
import com.cream.helper.core.net.msg.Message;
import com.cream.helper.core.net.msg.MessagePool;
import com.cream.helper.core.net.msg.MessageTemplatePool;
import com.cream.helper.obj.Ret;
import com.cream.helper.service.IMessageService;
import com.google.protobuf.GeneratedMessageV3;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MockComponent
public class MockMessageService implements IMessageService {

    private final RoleSessionManager roleSessionManager;

    private final MessageTemplatePool messageTemplatePool;

    private final MessagePool messagePool;

    @Autowired
    public MockMessageService(RoleSessionManager roleSessionManager,
                              MessageTemplatePool messageTemplatePool,
                              MessagePool messagePool) {
        this.roleSessionManager = roleSessionManager;
        this.messageTemplatePool = messageTemplatePool;
        this.messagePool = messagePool;
    }

    @Override
    public Ret<String> sendRequest(long rid, Message message) {
        if (roleSessionManager.isOffLine(rid)) {
            return Ret.err("角色不在线");
        }
        RoleSession roleSession = roleSessionManager.getRoleSession(rid);
        GameClient gameClient = roleSession.getGameClient();
        gameClient.sendMsg(message);
        return Ret.ok("发送成功");
    }

    @Override
    public Ret<List<Message<? extends GeneratedMessageV3>>> fetchAllReqTemplate() {
        return Ret.ok(messageTemplatePool.getAllReqTemplate());
    }

    @Override
    public Ret<List<Message<? extends GeneratedMessageV3>>> fetchAllResTemplate() {
        return Ret.ok(messageTemplatePool.getAllResTemplate());
    }

    @Override
    public Ret<List<Message<? extends GeneratedMessageV3>>> fetchAllResMsg(long rid) {
        if (roleSessionManager.isOffLine(rid)) {
            return Ret.err("角色不在线");
        }
        List<Message<? extends GeneratedMessageV3>> messages = messagePool.fetchAllResponse(rid);
        return Ret.ok(messages);
    }
}
