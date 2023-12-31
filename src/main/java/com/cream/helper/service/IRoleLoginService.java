package com.cream.helper.service;

import com.cream.helper.obj.bo.Role;
import com.cream.helper.obj.vo.Result;

import java.util.List;

public interface IRoleLoginService {
    Result<List<Role>> fetchRoleList(long userId);

    Result<Role> createRole(Role role);
}
