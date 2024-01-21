package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.core.net.UserSessionManager;
import com.cream.helper.core.net.common.GameNetSetup;
import com.cream.helper.mapper.mock.MockRoleMapper;
import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.bo.Role;
import com.cream.helper.obj.domain.bo.RoleEnterInfo;
import com.cream.helper.obj.domain.bo.RoleHeartInfo;
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
    public Ret<Role> createRole(Role role) {
        String name = role.getName();
        if (mockRoleMapper.containsName(name)) {
            return Ret.err("角色名称已存在");
        }
        // 初始化等级
        role.setLevel(1);
        mockRoleMapper.insert(role);
        return Ret.ok(role);
    }

    @Override
    public Ret<Role> deleteRole(Role role) {
        return null;
    }

    @Override
    public Ret<RoleEnterInfo> enterRole(Role role) {
        return Ret.ok(new RoleEnterInfo(role, null));
    }

    @Override
    public Ret<Role> exitRole(Role role) {
        return Ret.ok(role);
    }


    @Override
    public Ret<RoleHeartInfo> heart(Role role) {
        return null;
    }
}
