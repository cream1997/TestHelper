package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.core.net.RoleSessionManager;
import com.cream.helper.core.net.bo.RoleSession;
import com.cream.helper.core.net.client.GameClient;
import com.cream.helper.core.net.common.MsgPool;
import com.cream.helper.core.net.common.MsgTemplatePool;
import com.cream.helper.core.net.common.msg.base.Message;
import com.cream.helper.obj.Ret;
import com.cream.helper.service.IMessageService;
import com.google.protobuf.GeneratedMessageV3;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MockComponent
public class MockMessageService implements IMessageService {

    private final RoleSessionManager roleSessionManager;

    private final MsgTemplatePool msgTemplatePool;

    private final MsgPool msgPool;

    @Autowired
    public MockMessageService(RoleSessionManager roleSessionManager,
                              MsgTemplatePool msgTemplatePool,
                              MsgPool msgPool) {
        this.roleSessionManager = roleSessionManager;
        this.msgTemplatePool = msgTemplatePool;
        this.msgPool = msgPool;
    }

    @Override
    public Ret<String> sendRequest(long rid, Message message) {
        if (roleSessionManager.isOffLine(rid)) {
            return Ret.err("角色不在线");
        }
        RoleSession roleSession = roleSessionManager.getRoleSession(rid);
        GameClient gameClient = roleSession.getGameClient();
        return Ret.ok("发送成功");
    }

    @Override
    public Ret<List<Message<? extends GeneratedMessageV3>>> fetchAllReqTemplate() {
        return Ret.ok(msgTemplatePool.getAllReqTemplate());
    }

    @Override
    public Ret<List<Message<? extends GeneratedMessageV3>>> fetchAllResTemplate() {
        return Ret.ok(msgTemplatePool.getAllResTemplate());
    }

    @Override
    public Ret<List<Message<? extends GeneratedMessageV3>>> fetchAllResMsg(long rid) {
        if (roleSessionManager.isOffLine(rid)) {
            return Ret.err("角色不在线");
        }
        List<Message<? extends GeneratedMessageV3>> messages = msgPool.fetchAllResponse(rid);
        return Ret.ok(messages);
    }
}
