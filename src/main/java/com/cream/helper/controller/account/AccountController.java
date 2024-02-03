package com.cream.helper.controller.account;

import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.dto.LoginDTO;
import com.cream.helper.obj.domain.dto.account.SetDefaultServerDTO;
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
    public Ret<String> register(@RequestBody LoginDTO loginDTO) {
        accountService.register(loginDTO.getUsername(), loginDTO.getPassword());
        return Ret.ok("注册成功");
    }


    @PostMapping("/login")
    public Ret<AccountVO> login(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        return Ret.ok(accountService.login(username, password));
    }

    @PostMapping("/checkToken")
    public Ret<Boolean> checkToken(String token) {
        return Ret.ok(accountService.checkToken(token));
    }

    @PostMapping("/logout")
    public void logout(String username) {
        accountService.logout(username);
    }

    @PostMapping("/getDefaultServer")
    public Ret<String> getDefaultServer(@RequestBody long accountId) {
        return Ret.ok(accountService.getDefaultServer(accountId));
    }

    @PostMapping("/setDefaultServer")
    public Ret<String> setDefaultServer(@RequestBody SetDefaultServerDTO setDefaultServerDTO) {
        accountService.setDefaultServer(setDefaultServerDTO);
        return Ret.ok();
    }
}
