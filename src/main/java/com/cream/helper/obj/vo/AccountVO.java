package com.cream.helper.obj.vo;

import lombok.Data;

@Data
public class AccountVO {
    private final long id;
    private final String accountName;
    private final String token;

    public AccountVO(long id, String accountName, String token) {
        this.id = id;
        this.accountName = accountName;
        this.token = token;
    }
}
