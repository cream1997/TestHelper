package com.cream.helper.controller.account;

import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.vo.LoginUserInfoVO;
import com.cream.helper.obj.domain.vo.ServerVO;
import com.cream.helper.obj.domain.vo.UserVO;
import com.cream.helper.service.IGameLoginService;
import com.cream.helper.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;
    private final IGameLoginService gameLoginService;

    @Autowired
    public UserController(UserService userService, IGameLoginService gameLoginService) {
        this.userService = userService;
        this.gameLoginService = gameLoginService;
    }

    @PostMapping("/registerUser")
    public Ret<String> registerUser(@RequestBody UserVO userVO) {
        return userService.register(userVO.getAccountId(), userVO.getUsername(), userVO.getPassword());
    }


    @PostMapping("/loginUser")
    public Ret<LoginUserInfoVO> loginUser(@RequestBody UserVO userVO) {
        return userService.login(userVO.getAccountId(), userVO.getUsername(), userVO.getPassword());
    }

    @PostMapping("/userLogout")
    public Ret<String> userLogout(@RequestBody long uid) {
        return userService.logout(uid);
    }


    @PostMapping("/fetchUserAccounts")
    public Ret<List<UserVO>> fetchUserAccounts(@RequestBody long accountId) {
        return userService.fetchUserAccounts(accountId);
    }

    /**
     * 获取服务器列表
     */
    @PostMapping("/fetchServerList")
    public Ret<List<ServerVO>> fetchServerList() {
        return gameLoginService.fetchServerList();
    }

    @PostMapping("/unBindUser")
    public Ret<String> unBindUser(@RequestBody String username) {
        return userService.unBindUser(username);
    }
}
