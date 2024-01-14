package com.cream.helper.obj.dto;


import lombok.Data;

@Data
public class UserDTO {
    /**
     * 平台账号id
     */
    private long accountId;
    private String username;
    private String password;
}
