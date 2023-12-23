package com.cream.helper.controller;

import com.cream.helper.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public String register() {
        return accountService.register();
    }

    public String login() {
        return accountService.login();
    }

    public String logout() {
        return accountService.logout();
    }

    /**
     * 获取服务器列表
     */
    public String fetchServerList() {
        return accountService.fetchServerList();
    }
}
