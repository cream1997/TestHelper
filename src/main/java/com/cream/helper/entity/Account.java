package com.cream.helper.entity;

import lombok.Getter;

public class Account {
    @Getter
    private final long id;
    @Getter
    private final String username;
    @Getter
    private final String password;


    public Account(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
