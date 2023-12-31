package com.cream.helper.controller;

import com.cream.helper.obj.dto.UserDTO;
import com.cream.helper.obj.vo.Result;
import com.cream.helper.obj.vo.ServerItem;
import com.cream.helper.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerUser")
    public Result<String> registerUser(UserDTO userDTO) {
        return userService.register(userDTO.getUsername(), userDTO.getPassword());
    }


    @PostMapping("/userLogin")
    public Result userLogin(String username, String password) {
        return userService.login(username, password);
    }

    @PostMapping("/userLogout")
    public Result userLogout(long rid) {
        return userService.logout(rid);
    }

    /**
     * 获取服务器列表
     */
    @PostMapping("/fetchServerList")
    public Result<List<ServerItem>> fetchServerList() {
        return userService.fetchServerList();
    }
}
