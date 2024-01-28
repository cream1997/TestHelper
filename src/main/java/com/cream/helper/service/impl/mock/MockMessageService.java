package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.core.net.UserSessionManager;
import com.cream.helper.core.net.bo.UserSession;
import com.cream.helper.core.net.client.GameClient;
import com.cream.helper.core.net.common.MsgTemplatePool;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.vo.MsgVO;
import com.cream.helper.service.IMessageService;
import com.cream.helper.utils.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@MockComponent
public class MockMessageService implements IMessageService {

    private final UserSessionManager sessionManager;

    private final MsgTemplatePool msgTemplatePool;

    @Autowired
    public MockMessageService(UserSessionManager sessionManager,
                              MsgTemplatePool msgTemplatePool) {
        this.sessionManager = sessionManager;
        this.msgTemplatePool = msgTemplatePool;
    }

    @Override
    public Ret<String> sendRequest(long rid, MsgVO msgVO) {
        if (sessionManager.isOffLine(rid)) {
            return Ret.err("角色不在线");
        }
        UserSession userSession = sessionManager.getSession(rid);
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
    public Ret<List<MsgVO>> fetchAllResMsg(long uid) {
        if (sessionManager.isOffLine(uid)) {
            return Ret.err("角色不在线");
        }
        UserSession session = sessionManager.getSession(uid);
        GameClient gameClient = session.getGameClient();
        List<Message<?>> messages = gameClient.fetchAllMsg();
        if (NullUtil.isEmpty(messages)) {
            return Ret.ok(Collections.emptyList());
        }
        List<MsgVO> msgVOS = new ArrayList<>();
        for (Message<?> message : messages) {
            msgVOS.add(new MsgVO(message));
        }
        return Ret.ok(msgVOS);
    }
}
