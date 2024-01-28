package com.cream.helper.service.impl;

import com.cream.helper.core.exe.constant.gm.GmCmdType;
import com.cream.helper.core.net.UserSessionManager;
import com.cream.helper.core.net.bo.UserSession;
import com.cream.helper.core.net.client.GameClient;
import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.dto.ExeGmReq;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GmService {

    private final UserSessionManager sessionManager;

    public GmService(UserSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * 执行gm命令
     */
    public Ret<String> exeGmCmd(ExeGmReq exeGmReq) {
        long rid = exeGmReq.getRid();
        if (sessionManager.isOffLine(rid)) {
            return Ret.err("角色不在线");
        }
        UserSession userSession = sessionManager.getSession(rid);
        GameClient gameClient = userSession.getGameClient();
//        todo
//        gameClient.sendMsg();
        return Ret.ok("执行成功");
    }

    /**
     * 获取所有GM命令
     */
    public Ret<Collection<GmCmdType>> fetchAllGmCommand() {
        Collection<GmCmdType> allGmCmd = GmCmdType.getAllGmCmd();
        return Ret.ok(allGmCmd);
    }
}
