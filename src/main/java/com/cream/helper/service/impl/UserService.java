package com.cream.helper.service.impl;

import com.cream.helper.config.configuration.exception.CommonError;
import com.cream.helper.config.configuration.exception.CommonRunError;
import com.cream.helper.mapper.LocalUserMapper;
import com.cream.helper.obj.Ret;
import com.cream.helper.obj.entity.account.User;
import com.cream.helper.service.IGameLoginService;
import com.cream.helper.tools.account.FormCheckTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final LocalUserMapper localUserMapper;

    private final IGameLoginService gameLoginService;

    private final FormCheckTool formCheckTool;

    @Autowired
    public UserService(LocalUserMapper localUserMapper, IGameLoginService gameLoginService, FormCheckTool formCheckTool) {
        this.localUserMapper = localUserMapper;
        this.gameLoginService = gameLoginService;
        this.formCheckTool = formCheckTool;
    }

    /**
     * 注册账号
     */
    public Ret<String> register(long accountId, String username, String pwd) {
        if (accountId == 0L) {
            // fixme
            return Ret.err("未登录测试平台");
        }
        formCheckTool.checkNull(username, pwd);
        // 查看远端是否已注册
        try {
            gameLoginService.registerRemote(username, pwd);
        } catch (CommonError e) {
            return Ret.err(e.getMessage());
        }
        // 本地注册
        return localRegister(accountId, username, pwd, true);
    }


    private Ret<String> localRegister(long accountId, String username, String pwd, boolean deleteOldData) {
        // 添加到本地数据库, 删除本地可能存在的不一致数据
        if (deleteOldData) {
            localUserMapper.deleteByUsername(username);
        }
        User user = new User(accountId, username, pwd);
        localUserMapper.insert(user);
        return Ret.ok("注册成功");
    }


    public String login(long accountId, String username, String password) {
        // todo check accountId
        formCheckTool.checkNull(username, password);
        String gameLoginToken;
        try {
            gameLoginToken = gameLoginService.loginUser(username, password);
        } catch (CommonError e) {
            throw new RuntimeException(e);
        }
        // 校验与本地数据的一致性
        User localUser = localUserMapper.getUser(username);
        if (localUser == null) {
            // 注册本地
            localRegister(accountId, username, password, false);
        } else {
            if (localUser.getAccountId() != accountId) {
                throw new CommonRunError("该账号已绑定其它账户");
            }
            if (!Objects.equals(localUser.getPassword(), password)) {
                localRegister(accountId, username, password, true);
            }
        }
        return gameLoginToken;
    }

    /**
     * todo 没有引入Session机制时，不需要logout
     */
    public Ret<String> logout(long id) {
        return null;
    }


}
