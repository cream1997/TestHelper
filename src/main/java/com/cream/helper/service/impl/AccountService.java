package com.cream.helper.service.impl;

import com.cream.helper.mapper.AccountMapper;
import com.cream.helper.obj.Ret;
import com.cream.helper.obj.entity.account.Account;
import com.cream.helper.obj.vo.AccountVO;
import com.cream.helper.tools.JwtTool;
import com.cream.helper.tools.account.FormCheckTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试平台的账号服务
 */
@Service
public class AccountService {

    private final AccountMapper accountMapper;
    private final JwtTool jwtTool;
    private final FormCheckTool formCheckTool;

    @Autowired
    public AccountService(AccountMapper accountMapper, JwtTool jwtTool, FormCheckTool formCheckTool) {
        this.accountMapper = accountMapper;
        this.jwtTool = jwtTool;
        this.formCheckTool = formCheckTool;
    }

    public Ret<String> register(String username, String password) {
        formCheckTool.checkNull(username, password);
        Account account = accountMapper.getAccount(username);
        if (account != null) {
            return Ret.err(null, "账号已被注册");
        }
        accountMapper.insert(new Account(username, password));
        return Ret.ok("注册成功");
    }

    public Ret<AccountVO> login(String username, String password) {
        formCheckTool.checkNull(username, password);
        Account account = accountMapper.getAccount(username);
        if (account == null) {
            return Ret.err("账号不存在");
        }
        if (!password.equals(account.getPassword())) {
            return Ret.err("密码错误");
        } else {
            // 生成token
            String token = jwtTool.createToken(account.getId(), account.getAccountName());
            AccountVO accountVO = new AccountVO(account.getId(), account.getAccountName(), account.getPassword(), token);
            return Ret.ok(accountVO);
        }
    }

    public Ret logout(String username) {
        return null;
    }
}
