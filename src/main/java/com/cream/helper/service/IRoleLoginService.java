package com.cream.helper.service;

import com.cream.helper.obj.domain.bo.Role;
import com.cream.helper.obj.domain.bo.RoleHeartInfo;
import com.cream.helper.obj.domain.vo.role.RoleEnterVO;
import com.cream.helper.obj.domain.vo.role.RoleItemVO;

public interface IRoleLoginService {
    RoleItemVO createRole(RoleItemVO roleItemVO);

    RoleItemVO deleteRole(RoleItemVO role);

    RoleEnterVO enterRole(RoleItemVO roleItem);

    Role exitRole(Role role);

    RoleHeartInfo heart(Role role);
}
