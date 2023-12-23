package com.cream.helper.controller;

import com.cream.helper.pojo.Result;
import com.cream.helper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerUser")
    public Result registerUser(String username, String password) {
        return userService.register(username, password);
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
    public Result fetchServerList() {
        return userService.fetchServerList();
    }
}
