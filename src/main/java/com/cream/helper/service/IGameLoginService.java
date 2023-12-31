package com.cream.helper.service;

import com.cream.helper.obj.entity.account.User;

public interface IGameLoginService {
    User getRemoteUser(String username);

    User registerRemote(String username, String password);
}
