package com.cream.helper.obj.domain.vo.user;

import com.cream.helper.obj.domain.vo.role.RoleItemVO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginUserInfoVO {

    private final long uid;
    private final List<RoleItemVO> roleItems;
}
