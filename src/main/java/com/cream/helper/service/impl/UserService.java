package com.cream.helper.service.impl;

import com.cream.helper.config.configuration.exception.CommonError;
import com.cream.helper.config.configuration.exception.CommonRunError;
import com.cream.helper.core.net.client.GameClient;
import com.cream.helper.core.net.common.GameNetSetup;
import com.cream.helper.mapper.LocalUserMapper;
import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.vo.RoleVO;
import com.cream.helper.obj.domain.vo.UserVO;
import com.cream.helper.obj.entity.account.User;
import com.cream.helper.service.IGameLoginService;
import com.cream.helper.tools.account.FormCheckTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final LocalUserMapper localUserMapper;

    private final IGameLoginService gameLoginService;

    private final FormCheckTool formCheckTool;

    private final GameNetSetup gameNetSetup;

    @Autowired
    public UserService(LocalUserMapper localUserMapper,
                       IGameLoginService gameLoginService,
                       GameNetSetup gameNetSetup,
                       FormCheckTool formCheckTool) {
        this.localUserMapper = localUserMapper;
        this.gameLoginService = gameLoginService;
        this.gameNetSetup = gameNetSetup;
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


    public Ret<List<RoleVO>> login(long accountId, String username, String password) {
        // todo check accountId
        formCheckTool.checkNull(username, password);
        String gameLoginToken;
        try {
            gameLoginToken = gameLoginService.loginUser(username, password);
        } catch (CommonError e) {
            throw new RuntimeException(e);
        }
        // 校验与本地数据的一致性
        verifyLocal(accountId, username, password);
        GameClient gameClient;
        try {
            gameClient = new GameClient(gameNetSetup);
        } catch (CommonError e) {
            return Ret.err(e.getMessage());
        }
        // todo
        return null;
    }

    private void verifyLocal(long accountId, String username, String password) {
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
    }

    /**
     * todo 没有引入Session机制时，不需要logout
     */
    public Ret<String> logout(long id) {
        return null;
    }


    public Ret<List<UserVO>> fetchUserAccounts(long accountId) {
        List<User> users = localUserMapper.getUserAccounts(accountId);
        List<UserVO> allUserVO = Collections.emptyList();
        if (users != null) {
            allUserVO = users.stream().map(UserVO::new)
                    .collect(Collectors.toList());
        }
        return Ret.ok(allUserVO);
    }

    public Ret<String> unBindUser(String username) {
        localUserMapper.deleteByUsername(username);
        return Ret.ok("删除成功");
    }
}
