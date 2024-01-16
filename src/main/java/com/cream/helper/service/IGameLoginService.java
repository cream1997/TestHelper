package com.cream.helper.service;

import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.vo.ServerVO;
import com.cream.helper.obj.entity.account.User;

import java.util.List;

public interface IGameLoginService {
    User getRemoteUser(String username);

    User registerRemote(String username, String password);

    Ret<List<ServerVO>> fetchServerList();
}
