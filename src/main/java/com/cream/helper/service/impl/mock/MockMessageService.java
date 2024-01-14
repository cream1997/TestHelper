package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.core.net.GameClient;
import com.cream.helper.core.net.RoleSessionManager;
import com.cream.helper.core.net.bo.RoleSession;
import com.cream.helper.core.net.msg.Message;
import com.cream.helper.core.net.msg.MessagePool;
import com.cream.helper.core.net.msg.MessageTemplatePool;
import com.cream.helper.obj.vo.Ret;
import com.cream.helper.service.IMessageService;
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
            return Ret.fail("角色不在线");
        }
        RoleSession roleSession = roleSessionManager.getRoleSession(rid);
        GameClient gameClient = roleSession.getGameClient();
        gameClient.sendMsg(message);
        return Ret.success("发送成功");
    }

    @Override
    public Ret<List<Message>> fetchAllReqTemplate() {
        return Ret.success(messageTemplatePool.getAllReqTemplate());
    }

    @Override
    public Ret<List<Message>> fetchAllResTemplate() {
        return Ret.success(messageTemplatePool.getAllResTemplate());
    }

    @Override
    public Ret<List<Message>> fetchAllResMsg(long rid) {
        if (roleSessionManager.isOffLine(rid)) {
            return Ret.fail("角色不在线");
        }
        List<Message> messages = messagePool.fetchAllResponse(rid);
        return Ret.success(messages);
    }
}
