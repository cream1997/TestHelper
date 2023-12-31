package com.cream.helper.service.impl;

import com.cream.helper.mapper.UserMapper;
import com.cream.helper.obj.entity.account.User;
import com.cream.helper.obj.vo.Result;
import com.cream.helper.obj.vo.ServerItem;
import com.cream.helper.service.IRemoteUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserMapper userMapper;

    private final IRemoteUserService remoteUserService;

    @Autowired
    public UserService(UserMapper userMapper, IRemoteUserService remoteUserService) {
        this.userMapper = userMapper;
        this.remoteUserService = remoteUserService;
    }

    /**
     * 注册账号
     */
    public Result<String> register(String username, String password) {
        if (StringUtils.isAnyBlank(username, password)) {
            return Result.fail("用户名或密码不能为空");
        }
//        1.查看远端是否已注册
        User remoteUser = remoteUserService.getRemoteUser(username);
        if (remoteUser != null) {
            return localRegister(password, remoteUser);
        } else {
            // 注册
            return fullRegister(username, password);
        }
    }

    /**
     * 远端有数据，只注册本地，本地数据保持与远端一致
     */
    private Result<String> localRegister(String password, User remoteUser) {
        if (!Objects.equals(password, remoteUser.getPassword())) {
            return Result.fail("账号已被注册");
        }
        // 查看是否重复注册
        User localUser = userMapper.getUser(remoteUser.getUsername());
        if (localUser == null) {
            // 注册本地
            return doLocalRegister(remoteUser, false);
        } else {
            if (userNotEqual(localUser, remoteUser)) {
                // 数据不一致已远端为准
                return doLocalRegister(localUser, true);
            } else {
                return Result.fail("重复注册");
            }
        }
    }

    private Result<String> fullRegister(String username, String password) {
        User user = remoteUserService.registerRemote(username, password);
        return doLocalRegister(user, true);
    }

    private Result<String> doLocalRegister(User user, boolean deleteOldData) {
        // 添加到本地数据库, 删除本地可能存在的不一致数据
        if (deleteOldData) {
            userMapper.deleteById(user.getId());
        }
        userMapper.insert(user);
        return Result.success("注册成功");
    }

    /**
     * 本地数据与远端不一致
     */
    private boolean userNotEqual(User localUser, User remoteUser) {
        return localUser.getId() != remoteUser.getId() || !Objects.equals(localUser.getPassword(), remoteUser.getPassword());
    }


    public Result login(String username, String password) {
        return null;
    }

    public Result logout(long rid) {
        return null;
    }

    /**
     * 抓取可用的服务器列表
     */
    public Result<List<ServerItem>> fetchServerList() {
        /*
         * todo 模拟数据, 待修改
         */
        ServerItem dev = new ServerItem("研发服", "192.168.1.1", 6666);
        ServerItem beta = new ServerItem("beta服", "192.168.2.2", 8888);
        ServerItem trunk = new ServerItem("稳定服", "192.168.3.3", 6688);
        List<ServerItem> serverList = new ArrayList<>(Arrays.asList(dev, beta, trunk));
        return Result.success(serverList);
    }
}
