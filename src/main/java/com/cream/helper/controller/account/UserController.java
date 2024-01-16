package com.cream.helper.controller.account;

import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.bo.LoginInfo;
import com.cream.helper.obj.domain.dto.UserDTO;
import com.cream.helper.obj.domain.vo.ServerItem;
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
    public Ret<String> registerUser(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO.getAccountId(), userDTO.getUsername(), userDTO.getPassword());
    }


    @PostMapping("/userLogin")
    public Ret<LoginInfo> userLogin(String username, String password) {
        return userService.login(username, password);
    }

    @PostMapping("/userLogout")
    public Ret<String> userLogout(long id) {
        return userService.logout(id);
    }

    /**
     * 获取服务器列表
     */
    @PostMapping("/fetchServerList")
    public Ret<List<ServerItem>> fetchServerList() {
        return gameLoginService.fetchServerList();
    }
}
