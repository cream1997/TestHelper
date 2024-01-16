package com.cream.helper.controller.account;

import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.dto.LoginDTO;
import com.cream.helper.obj.domain.vo.AccountVO;
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
    public Ret<String> register(@RequestBody LoginDTO loginDTO) {
        return accountService.register(loginDTO.getUsername(), loginDTO.getPassword());
    }


    @PostMapping("/login")
    public Ret<AccountVO> login(@RequestBody LoginDTO loginDTO) {
        return accountService.login(loginDTO.getUsername(), loginDTO.getPassword());
    }

    @PostMapping("/checkToken")
    public Ret<Boolean> checkToken(String token) {
        return accountService.checkToken(token);
    }

    @PostMapping("/logout")
    public Ret<Object> logout(String username) {
        return accountService.logout(username);
    }
}
