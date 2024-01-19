package com.cream.helper.controller.account;

import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.vo.ServerVO;
import com.cream.helper.obj.domain.vo.UserVO;
import com.cream.helper.service.IGameLoginService;
import com.cream.helper.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    @PostMapping("/userLogin")
    public String userLogin(long accountId, String username, String password) {
        return userService.login(accountId, username, password);
    }

    @PostMapping("/userLogout")
    public Ret<String> userLogout(long id) {
        return userService.logout(id);
    }

    /**
     * 获取服务器列表
     */
    @PostMapping("/fetchServerList")
    public Ret<List<ServerVO>> fetchServerList() {
        return gameLoginService.fetchServerList();
    }
}
