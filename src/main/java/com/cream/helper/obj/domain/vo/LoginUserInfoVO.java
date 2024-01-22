package com.cream.helper.obj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginUserInfoVO {

    private final long uid;
    private final List<RoleItemVO> roleItems;
}
