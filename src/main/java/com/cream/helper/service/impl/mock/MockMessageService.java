package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.config.configuration.exception.RunErr;
import com.cream.helper.core.net.UserSessionManager;
import com.cream.helper.core.net.bo.UserSession;
import com.cream.helper.core.net.client.GameClient;
import com.cream.helper.core.net.common.MsgTemplatePool;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.msg.req.ReqHeartMsg;
import com.cream.helper.core.net.proto.clazz.CommonProto;
import com.cream.helper.obj.domain.vo.MsgVO;
import com.cream.helper.service.IMessageService;
import com.cream.helper.utils.NullUtil;
import com.cream.helper.utils.Times;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@MockComponent
public class MockMessageService implements IMessageService {

    private final UserSessionManager sessionManager;

    private final MsgTemplatePool msgTemplatePool;
    /**
     * 模糊搜索最大返回数量
     */
    private final static int FUZZY_SEARCH_COUNT = 6;

    @Autowired
    public MockMessageService(UserSessionManager sessionManager,
                              MsgTemplatePool msgTemplatePool) {
        this.sessionManager = sessionManager;
        this.msgTemplatePool = msgTemplatePool;
    }

    @Override
    public void sendRequest(long rid, MsgVO msgVO) {
        if (sessionManager.isOffLine(rid)) {
            throw new RunErr("角色不在线");
        }
        UserSession userSession = sessionManager.getSession(rid);
        GameClient gameClient = userSession.getGameClient();
    }

    @Override
    public List<Message<?>> fetchAllReqTemplate() {
        return msgTemplatePool.getAllReqTemplate();
    }

    @Override
    public List<Message<?>> fetchAllResTemplate() {
        return msgTemplatePool.getAllResTemplate();
    }

    @Override
    public List<MsgVO> heartBeat(long uid) {
        if (sessionManager.isOffLine(uid)) {
            throw new RunErr("角色不在线");
        }
        UserSession session = sessionManager.getSession(uid);
        // 心跳保活
        session.setLastHeartTime(Times.now());
        GameClient gameClient = session.getGameClient();
        gameClient.sendMsg(new ReqHeartMsg(() ->
                CommonProto.Heart.newBuilder()
                        .setTime(Times.now())
                        .build()));
        List<Message<?>> messages = gameClient.fetchAllMsg();
        if (NullUtil.isEmpty(messages)) {
            return Collections.emptyList();
        }
        List<MsgVO> msgVOS = new ArrayList<>();
        for (Message<?> message : messages) {
            msgVOS.add(new MsgVO(message));
        }
        return msgVOS;
    }

    @Override
    public List<Message<?>> searchMsgTemplate(String msgNameKey) {
        return msgTemplatePool.searchMsgTemplates(msgNameKey, FUZZY_SEARCH_COUNT);
    }
}
