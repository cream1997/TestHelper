package com.cream.helper.service.impl;

import com.cream.helper.core.exe.constant.gm.GmCmdType;
import com.cream.helper.core.net.GameClient;
import com.cream.helper.core.net.RoleSessionManager;
import com.cream.helper.core.net.bo.RoleSession;
import com.cream.helper.obj.dto.ExeGmReq;
import com.cream.helper.obj.vo.Result;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GmService {

    private final RoleSessionManager sessionManager;

    public GmService(RoleSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * 执行gm命令
     */
    public Result<String> exeGmCmd(ExeGmReq exeGmReq) {
        long rid = exeGmReq.getRid();
        if (sessionManager.isOffLine(rid)) {
            return Result.fail("角色不在线");
        }
        RoleSession roleSession = sessionManager.getRoleSession(rid);
        GameClient gameClient = roleSession.getGameClient();
//        todo
//        gameClient.sendMsg();
        return Result.success("执行成功");
    }

    /**
     * 获取所有GM命令
     */
    public Result<Collection<GmCmdType>> fetchAllGmCommand() {
        Collection<GmCmdType> allGmCmd = GmCmdType.getAllGmCmd();
        return Result.success(allGmCmd);
    }
}
