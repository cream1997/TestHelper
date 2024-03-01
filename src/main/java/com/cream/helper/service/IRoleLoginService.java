package com.cream.helper.service;

import com.cream.helper.obj.domain.vo.role.RoleEnterVO;
import com.cream.helper.obj.domain.vo.role.RoleItemVO;
import com.cream.helper.service.bo.Role;
import com.cream.helper.service.bo.RoleHeartInfo;

public interface IRoleLoginService {
    RoleItemVO createRole(RoleItemVO roleItemVO);

    RoleItemVO deleteRole(RoleItemVO role);

    RoleEnterVO enterRole(RoleItemVO roleItem);

    Role exitRole(Role role);

    RoleHeartInfo heart(Role role);
}
