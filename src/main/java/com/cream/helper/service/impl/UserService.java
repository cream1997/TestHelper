package com.cream.helper.service.impl;

import com.cream.helper.config.configuration.exception.Err;
import com.cream.helper.config.configuration.exception.RunErr;
import com.cream.helper.core.net.UserSessionManager;
import com.cream.helper.core.net.bo.UserSession;
import com.cream.helper.core.net.client.GameClient;
import com.cream.helper.core.net.common.GameNetSetup;
import com.cream.helper.core.net.msg.req.ReqLoginMsg;
import com.cream.helper.core.net.msg.res.ResLoginMsg;
import com.cream.helper.core.net.proto.clazz.CommonProto;
import com.cream.helper.mapper.LocalUserMapper;
import com.cream.helper.obj.domain.vo.ServerVO;
import com.cream.helper.obj.domain.vo.role.RoleItemVO;
import com.cream.helper.obj.domain.vo.user.LoginUserInfoVO;
import com.cream.helper.obj.domain.vo.user.UserVO;
import com.cream.helper.obj.entity.account.User;
import com.cream.helper.service.IGameLoginService;
import com.cream.helper.utils.NullUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    private final LocalUserMapper localUserMapper;

    private final IGameLoginService gameLoginService;

    private final GameNetSetup netSetup;

    private final UserSessionManager userSessionManager;

    @Autowired
    public UserService(LocalUserMapper localUserMapper,
                       IGameLoginService gameLoginService,
                       GameNetSetup netSetup,
                       UserSessionManager userSessionManager) {
        this.localUserMapper = localUserMapper;
        this.gameLoginService = gameLoginService;
        this.netSetup = netSetup;
        this.userSessionManager = userSessionManager;
    }

    /**
     * 注册账号
     */
    public void register(long accountId, String username, String pwd) {
        if (accountId == 0L) {
            // fixme
            throw new RunErr("未登录测试平台");
        }
        NullUtil.checkNull(username, pwd);
        // 查看远端是否已注册
        try {
            gameLoginService.registerRemote(username, pwd);
        } catch (Err e) {
            throw new RunErr(e);
        }
        // 本地注册
        localRegister(accountId, username, pwd, true);
    }


    private void localRegister(long accountId, String username, String pwd, boolean deleteOldData) {
        // 添加到本地数据库, 删除本地可能存在的不一致数据
        if (deleteOldData) {
            localUserMapper.deleteByUsername(username);
        }
        User user = new User(accountId, username, pwd);
        localUserMapper.insert(user);
    }


    public LoginUserInfoVO login(UserVO userVO, ServerVO serverVO) {
        // todo check accountId
        String username = userVO.getUsername();
        String password = userVO.getPassword();
        NullUtil.checkNull(username, password);
        String gameLoginToken;
        try {
            gameLoginToken = gameLoginService.loginUser(username, password);
        } catch (Err e) {
            throw new RuntimeException(e);
        }
        // 校验与本地数据的一致性
        verifyLocal(userVO.getAccountId(), username, password);
        GameClient gameClient;
        try {
            gameClient = new GameClient(netSetup, serverVO);
        } catch (Err e) {
            throw new RunErr(e);
        }
        ReqLoginMsg reqLoginMsg = new ReqLoginMsg(() ->
                CommonProto.LoginReq.newBuilder()
                        .setUsername(username)
                        .setPassword(password)
                        .build());
        try {
            ResLoginMsg resLoginMsg = gameClient.sendMsg(reqLoginMsg, ResLoginMsg.class);

            CommonProto.LoginRes data = resLoginMsg.getData();
            long uid = data.getUid();
            // 登录成功，创建session
            userSessionManager.addSession(new UserSession(uid, gameClient));
            List<RoleItemVO> roleItemVOS = new ArrayList<>();
            for (CommonProto.Role role : data.getRoleList()) {
                RoleItemVO roleItemVO = new RoleItemVO(uid, role.getRid(), role.getRoleName(), role.getLevel(), role.getCareer());
                roleItemVOS.add(roleItemVO);
            }
            return new LoginUserInfoVO(uid, roleItemVOS);
        } catch (Err e) {
            throw new RunErr(e);
        }
    }

    private void verifyLocal(long accountId, String username, String password) {
        User localUser = localUserMapper.getUser(username);
        if (localUser == null) {
            // 注册本地
            localRegister(accountId, username, password, false);
        } else {
            if (localUser.getAccountId() != accountId) {
                throw new RunErr("该账号已绑定其它账户");
            }
            if (!Objects.equals(localUser.getPassword(), password)) {
                localRegister(accountId, username, password, true);
            }
        }
    }

    public void logout(long uid) {
        userSessionManager.removeSession(uid);
        log.info("用户{}退出", uid);
    }


    public List<UserVO> fetchUserAccounts(long accountId) {
        List<User> users = localUserMapper.getUserAccounts(accountId);
        List<UserVO> allUserVO = Collections.emptyList();
        if (users != null) {
            allUserVO = users.stream().map(UserVO::new)
                    .collect(Collectors.toList());
        }
        return allUserVO;
    }

    public void unBindUser(String username) {
        localUserMapper.deleteByUsername(username);
    }
}
