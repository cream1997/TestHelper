package com.cream.helper.obj.domain.vo;

import lombok.Data;

@Data
public class AccountVO {
    private final long id;
    private final String accountName;
    private final String password;
    private final String token;

    public AccountVO(long id, String accountName, String password, String token) {
        this.id = id;
        this.accountName = accountName;
        this.password = password;
        this.token = token;
    }
}
