package com.cream.helper.service.impl;

import com.cream.helper.mapper.LocalUserMapper;
import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.bo.LoginInfo;
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
    public Ret<String> register(long accountId, String username, String password) {
        if (accountId == 0L) {
            // fixme
            return Ret.err("未登录测试平台");
        }
        formCheckTool.checkNull(username, password);
//        1.查看远端是否已注册
        User remoteUser = gameLoginService.getRemoteUser(username);
        if (remoteUser != null) {
            return localRegister(accountId, password, remoteUser);
        } else {
            // 注册
            return fullRegister(accountId, username, password);
        }
    }

    /**
     * 远端有数据，只注册本地，本地数据保持与远端一致
     */
    private Ret<String> localRegister(long accountId, String password, User remoteUser) {
        if (!Objects.equals(password, remoteUser.getPassword())) {
            return Ret.err("账号已被注册");
        }
        // 查看是否重复注册
        User localUser = localUserMapper.getUser(remoteUser.getUsername());
        if (localUser == null) {
            // 注册本地
            return doLocalRegister(remoteUser, false);
        } else {
            if (userNotEqual(localUser, remoteUser)) {
                // 数据不一致已远端为准
                return doLocalRegister(localUser, true);
            } else {
                return Ret.err("重复注册");
            }
        }
    }

    private Ret<String> fullRegister(long accountId, String username, String password) {
        User user = gameLoginService.registerRemote(username, password);
        return doLocalRegister(user, true);
    }

    private Ret<String> doLocalRegister(User user, boolean deleteOldData) {
        // 添加到本地数据库, 删除本地可能存在的不一致数据
        if (deleteOldData) {
            localUserMapper.deleteById(user.getId());
        }
        localUserMapper.insert(user);
        return Ret.ok("注册成功");
    }

    /**
     * 本地数据与远端不一致
     */
    private boolean userNotEqual(User localUser, User remoteUser) {
        return localUser.getId() != remoteUser.getId() ||
                !Objects.equals(localUser.getPassword(), remoteUser.getPassword());
    }


    public Ret<LoginInfo> login(String username, String password) {
        formCheckTool.checkNull(username, password);
        User remoteUser = gameLoginService.getRemoteUser(username);
        if (remoteUser == null) {
            return Ret.err("账号不存在");
        }
        if (!Objects.equals(password, remoteUser.getPassword())) {
            return Ret.err("密码错误");
        }
        // 校验与本地数据的一致性
        User localUser = localUserMapper.getUser(username);
        if (localUser == null) {
            // 注册本地
            doLocalRegister(remoteUser, false);
        } else {
            if (userNotEqual(localUser, remoteUser)) {
                doLocalRegister(remoteUser, true);
            }
        }
        // todo 扩展信息待补充
        return Ret.ok(new LoginInfo(remoteUser, null));
    }

    /**
     * todo 没有引入Session机制时，不需要logout
     */
    public Ret<String> logout(long id) {
        return null;
    }


}
