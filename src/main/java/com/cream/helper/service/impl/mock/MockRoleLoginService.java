package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.core.net.GameClient;
import com.cream.helper.core.net.RoleSessionManager;
import com.cream.helper.mapper.mock.MockRoleMapper;
import com.cream.helper.obj.bo.Role;
import com.cream.helper.obj.bo.RoleEnterInfo;
import com.cream.helper.obj.bo.RoleHeartInfo;
import com.cream.helper.obj.vo.Ret;
import com.cream.helper.service.IRoleLoginService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MockComponent
public class MockRoleLoginService implements IRoleLoginService {

    private final MockRoleMapper mockRoleMapper;

    private final RoleSessionManager sessionManager;

    @Autowired
    public MockRoleLoginService(MockRoleMapper mockRoleMapper, RoleSessionManager sessionManager) {
        this.mockRoleMapper = mockRoleMapper;
        this.sessionManager = sessionManager;
    }

    @Override
    public Ret<List<Role>> fetchRoleList(long userId) {
        List<Role> roleList = mockRoleMapper.getRoleList(userId);
        return Ret.success(roleList);
    }

    @Override
    public Ret<Role> createRole(Role role) {
        String name = role.getName();
        if (mockRoleMapper.containsName(name)) {
            return Ret.fail("角色名称已存在");
        }
        // 初始化等级
        role.setLevel(1);
        mockRoleMapper.insert(role);
        return Ret.success(role);
    }

    @Override
    public Ret<Role> deleteRole(Role role) {
        if (role.getId() == null) {
            return Ret.fail("删除失败");
        }
        if (mockRoleMapper.deleteRole(role.getId(), role.getUserId())) {
            return Ret.success(role);
        } else {
            return Ret.fail("删除失败");
        }
    }

    @Override
    public Ret<RoleEnterInfo> enterRole(Role role) {
        if (!mockRoleMapper.containsRole(role.getId(), role.getUserId())) {
            return Ret.fail("角色不存在");
        }
        GameClient gameClient = new GameClient();
        sessionManager.addOnline(role, gameClient);
        return Ret.success(new RoleEnterInfo(role, null));
    }

    @Override
    public Ret<Role> exitRole(Role role) {
        sessionManager.removeOnline(role.getId());
        return Ret.success(role);
    }


    @Override
    public Ret<RoleHeartInfo> heart(Role role) {
        if (!sessionManager.isOffLine(role.getId())) {
            return Ret.fail("角色不在线");
        }
        return sessionManager.heart(role.getId());
    }
}
