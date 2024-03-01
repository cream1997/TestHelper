package com.cream.helper.service.bo;

import com.cream.helper.obj.entity.account.User;
import lombok.Getter;


@Getter
public class LoginInfo {
    private final User user;

    private final Object ext;

    public LoginInfo(User user, Object ext) {
        this.user = user;
        this.ext = ext;
    }
}
