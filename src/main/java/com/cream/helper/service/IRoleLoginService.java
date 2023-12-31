package com.cream.helper.service;

import com.cream.helper.obj.bo.Role;
import com.cream.helper.obj.bo.RoleEnterInfo;
import com.cream.helper.obj.bo.RoleHeartInfo;
import com.cream.helper.obj.vo.Result;

import java.util.List;

public interface IRoleLoginService {
    Result<List<Role>> fetchRoleList(long userId);

    Result<Role> createRole(Role role);

    Result<Role> deleteRole(Role role);

    Result<RoleEnterInfo> enterRole(Role role);

    Result<Role> exitRole(Role role);

    Result<RoleHeartInfo> heart(Role role);
}
