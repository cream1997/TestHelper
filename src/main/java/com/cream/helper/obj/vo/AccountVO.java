package com.cream.helper.obj.vo;

import lombok.Data;

@Data
public class AccountVO {
    private final long accountId;
    private final String accountName;
    private final String token;

    public AccountVO(long accountId, String accountName, String token) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.token = token;
    }
}
