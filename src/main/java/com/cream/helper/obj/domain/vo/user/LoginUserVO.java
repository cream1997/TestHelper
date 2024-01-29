package com.cream.helper.obj.domain.vo.user;

import com.cream.helper.obj.domain.vo.ServerVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginUserVO {
    private final UserVO userVO;
    private final ServerVO serverVO;
}
