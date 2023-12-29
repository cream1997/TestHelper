package com.cream.helper.controller;

import com.cream.helper.obj.dto.LoginDTO;
import com.cream.helper.pojo.Result;
import com.cream.helper.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试助手的账号控制器
 */
@CrossOrigin
@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/register")
    public Result register(@RequestBody LoginDTO loginDTO) {
        return accountService.register(loginDTO.getUsername(), loginDTO.getPassword());
    }


    @PostMapping("/login")
    public Result login(String username, String password) {
        return accountService.login(username, password);
    }

    @PostMapping("/logout")
    public Result logout(String username) {
        return accountService.logout(username);
    }
}
