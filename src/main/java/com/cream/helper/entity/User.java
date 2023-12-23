package com.cream.helper.entity;

import lombok.Getter;

public class User {
    @Getter
    private final int id;
    @Getter
    private final String username;
    @Getter
    private final String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
