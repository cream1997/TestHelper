package com.cream.helper.obj.domain.vo;


import lombok.Data;

@Data
public class UserVO {
    /**
     * 平台账号id
     */
    private long accountId;
    private String username;
    private String password;
}
