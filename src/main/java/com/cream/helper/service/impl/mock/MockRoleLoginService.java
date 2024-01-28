package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.core.net.UserSessionManager;
import com.cream.helper.core.net.common.GameNetSetup;
import com.cream.helper.mapper.mock.MockRoleMapper;
import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.bo.Role;
import com.cream.helper.obj.domain.bo.RoleEnterInfo;
import com.cream.helper.obj.domain.bo.RoleHeartInfo;
import com.cream.helper.obj.domain.vo.RoleItemVO;
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
    public Ret<RoleItemVO> createRole(RoleItemVO roleItemVO) {
        String roleName = roleItemVO.getRoleName();
        if (mockRoleMapper.containsName(roleName)) {
            return Ret.err("角色名称已存在");
        }
        Role role = new Role(roleItemVO.getUid(), roleName, 1, 1);
        // 初始化等级
        mockRoleMapper.insert(role);
        RoleItemVO createRole = new RoleItemVO(role.getUserId(), role.getId(), role.getName(), role.getLevel(), role.getCareer() + "");
        return Ret.ok(createRole);
    }

    @Override
    public Ret<RoleItemVO> deleteRole(RoleItemVO roleItemVO) {
        int deleteNum = mockRoleMapper.deleteById(roleItemVO.getRid());
        if (deleteNum > 0) {
            return Ret.ok(roleItemVO);
        } else {
            return Ret.err("删除失败");
        }
    }

    @Override
    public Ret<RoleEnterInfo> enterRole(RoleItemVO role) {
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
