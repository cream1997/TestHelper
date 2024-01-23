package com.cream.helper.service;

import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.bo.Role;
import com.cream.helper.obj.domain.bo.RoleEnterInfo;
import com.cream.helper.obj.domain.bo.RoleHeartInfo;
import com.cream.helper.obj.domain.vo.RoleItemVO;

public interface IRoleLoginService {
    Ret<Role> createRole(Role role);

    Ret<Role> deleteRole(Role role);

    Ret<RoleEnterInfo> enterRole(RoleItemVO roleItem);

    Ret<Role> exitRole(Role role);

    Ret<RoleHeartInfo> heart(Role role);
}
