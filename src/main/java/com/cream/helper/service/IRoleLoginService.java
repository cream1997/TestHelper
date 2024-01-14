package com.cream.helper.service;

import com.cream.helper.obj.Ret;
import com.cream.helper.obj.bo.Role;
import com.cream.helper.obj.bo.RoleEnterInfo;
import com.cream.helper.obj.bo.RoleHeartInfo;

import java.util.List;

public interface IRoleLoginService {
    Ret<List<Role>> fetchRoleList(long userId);

    Ret<Role> createRole(Role role);

    Ret<Role> deleteRole(Role role);

    Ret<RoleEnterInfo> enterRole(Role role);

    Ret<Role> exitRole(Role role);

    Ret<RoleHeartInfo> heart(Role role);
}
