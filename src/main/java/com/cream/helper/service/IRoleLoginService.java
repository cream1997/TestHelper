package com.cream.helper.service;

import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.bo.Role;
import com.cream.helper.obj.domain.bo.RoleHeartInfo;
import com.cream.helper.obj.domain.vo.RoleEnterVO;
import com.cream.helper.obj.domain.vo.RoleItemVO;

public interface IRoleLoginService {
    Ret<RoleItemVO> createRole(RoleItemVO roleItemVO);

    Ret<RoleItemVO> deleteRole(RoleItemVO role);

    Ret<RoleEnterVO> enterRole(RoleItemVO roleItem);

    Ret<Role> exitRole(Role role);

    Ret<RoleHeartInfo> heart(Role role);
}
