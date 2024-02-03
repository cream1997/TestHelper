package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.config.configuration.exception.Err;
import com.cream.helper.config.configuration.exception.RunErr;
import com.cream.helper.core.net.UserSessionManager;
import com.cream.helper.core.net.bo.UserSession;
import com.cream.helper.core.net.client.GameClient;
import com.cream.helper.core.net.common.GameNetSetup;
import com.cream.helper.core.net.msg.req.ReqEnterRoleMsg;
import com.cream.helper.core.net.msg.res.ResEnterRoleMsg;
import com.cream.helper.core.net.proto.clazz.CommonProto;
import com.cream.helper.mapper.mock.MockRoleMapper;
import com.cream.helper.obj.domain.bo.Role;
import com.cream.helper.obj.domain.bo.RoleHeartInfo;
import com.cream.helper.obj.domain.vo.role.RoleEnterVO;
import com.cream.helper.obj.domain.vo.role.RoleItemVO;
import com.cream.helper.obj.pojo.Position;
import com.cream.helper.service.IRoleLoginService;
import org.springframework.beans.factory.annotation.Autowired;

@MockComponent
public class MockRoleLoginService implements IRoleLoginService {

    private final MockRoleMapper mockRoleMapper;

    private final UserSessionManager sessionManager;

    private final GameNetSetup gameNetSetup;


    @Autowired
    public MockRoleLoginService(MockRoleMapper mockRoleMapper,
                                UserSessionManager sessionManager,
                                GameNetSetup gameNetSetup) {
        this.mockRoleMapper = mockRoleMapper;
        this.sessionManager = sessionManager;
        this.gameNetSetup = gameNetSetup;
    }

    @Override
    public RoleItemVO createRole(RoleItemVO roleItemVO) {
        String roleName = roleItemVO.getRoleName();
        if (mockRoleMapper.containsName(roleName)) {
            throw new RunErr("角色名称已存在");
        }
        Role role = new Role(roleItemVO.getUid(), roleName, 1, 1);
        // 初始化等级
        mockRoleMapper.insert(role);
        return new RoleItemVO(role.getUserId(), role.getId(), role.getName(), role.getLevel(), role.getCareer() + "");
    }

    @Override
    public RoleItemVO deleteRole(RoleItemVO roleItemVO) {
        int deleteNum = mockRoleMapper.deleteById(roleItemVO.getRid());
        if (deleteNum > 0) {
            return roleItemVO;
        } else {
            throw new RunErr("删除失败");
        }
    }

    @Override
    public RoleEnterVO enterRole(RoleItemVO role) {
        long uid = role.getUid();
        UserSession session = sessionManager.getSession(uid);
        if (session == null) {
            throw new RunErr("用户未登录");
        }
        GameClient gameClient = session.getGameClient();
        ReqEnterRoleMsg reqEnterRoleMsg = new ReqEnterRoleMsg(() ->
                CommonProto.EnterRoleReq.newBuilder()
                        .setRid(role.getRid())
                        .setUid(role.getUid())
                        .build());
        try {
            ResEnterRoleMsg enterRoleRes = gameClient.sendMsg(reqEnterRoleMsg, ResEnterRoleMsg.class);
            return getRoleEnterVO(enterRoleRes);
        } catch (Err e) {
            throw new RunErr(e.getMessage());
        }
    }

    private RoleEnterVO getRoleEnterVO(ResEnterRoleMsg enterRoleRes) {
        CommonProto.EnterRoleRes data = enterRoleRes.getData();
        CommonProto.Role role = data.getRole();
        long serverTime = data.getServerTime();
        CommonProto.Position positionProto = data.getPosition();
        RoleItemVO roleItemVO = new RoleItemVO(data.getUid(), role.getRid(), role.getRoleName(), role.getLevel(), role.getCareer() + "");
        Position position = new Position(positionProto.getMapName(), positionProto.getMapLine(), positionProto.getXy().getX(), positionProto.getXy().getY());
        return new RoleEnterVO(roleItemVO, serverTime, position);
    }

    @Override
    public Role exitRole(Role role) {
        return role;
    }


    @Override
    public RoleHeartInfo heart(Role role) {
        return null;
    }
}
