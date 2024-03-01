package com.cream.helper.controller.account;

import com.cream.helper.obj.domain.dto.LoginVO;
import com.cream.helper.obj.domain.vo.account.AccountVO;
import com.cream.helper.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试助手的账号控制器
 */
@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/register")
    public String register(@RequestBody LoginVO loginVO) {
        accountService.register(loginVO.getUsername(), loginVO.getPassword());
        return "注册成功";
    }


    @PostMapping("/login")
    public AccountVO login(@RequestBody LoginVO loginVO) {
        String username = loginVO.getUsername();
        String password = loginVO.getPassword();
        return accountService.login(username, password);
    }

    @PostMapping("/checkToken")
    public boolean checkToken(String token) {
        return accountService.checkToken(token);
    }

    @PostMapping("/logout")
    public void logout(String username) {
        accountService.logout(username);
    }
}
