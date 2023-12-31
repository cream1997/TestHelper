package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.mapper.mock.MockRoleMapper;
import com.cream.helper.obj.bo.Role;
import com.cream.helper.obj.vo.Result;
import com.cream.helper.service.IRoleLoginService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MockComponent
public class MockRoleLoginService implements IRoleLoginService {

    private final MockRoleMapper mockRoleMapper;

    @Autowired
    public MockRoleLoginService(MockRoleMapper mockRoleMapper) {
        this.mockRoleMapper = mockRoleMapper;
    }

    @Override
    public Result<List<Role>> fetchRoleList(long userId) {
        List<Role> roleList = mockRoleMapper.getRoleList(userId);
        return Result.success(roleList);
    }

    @Override
    public Result<Role> createRole(Role role) {
        String name = role.getName();
        if (mockRoleMapper.containsName(name)) {
            return Result.fail("角色名称已存在");
        }
        // 初始化等级
        role.setLevel(1);
        mockRoleMapper.insert(role);
        return Result.success(role);
    }
}
