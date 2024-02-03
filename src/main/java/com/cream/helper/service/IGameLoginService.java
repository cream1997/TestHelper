package com.cream.helper.service;

import com.cream.helper.config.configuration.exception.Err;
import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.vo.ServerVO;

import java.util.List;

public interface IGameLoginService {
    String loginUser(String username, String password) throws Err;

    void registerRemote(String username, String password) throws Err;

    Ret<List<ServerVO>> fetchServerList();
}
