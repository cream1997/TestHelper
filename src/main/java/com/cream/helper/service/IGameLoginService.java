package com.cream.helper.service;

import com.cream.helper.config.configuration.exception.CommonError;
import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.vo.ServerVO;

import java.util.List;

public interface IGameLoginService {
    String loginUser(String username, String password) throws CommonError;

    void registerRemote(String username, String password) throws CommonError;

    Ret<List<ServerVO>> fetchServerList();
}
