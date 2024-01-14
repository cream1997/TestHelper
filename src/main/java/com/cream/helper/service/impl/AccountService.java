package com.cream.helper.service.impl;

import com.cream.helper.mapper.AccountMapper;
import com.cream.helper.obj.entity.account.Account;
import com.cream.helper.obj.vo.Ret;
import com.cream.helper.utils.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试平台的账号服务
 */
@Service
public class AccountService {

    private final AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public Ret<String> register(String username, String password) {
        if (NullUtil.isAnyBlank(username, password)) {
            return Ret.err(null, "用户名或密码不能为空");
        }
        Account account = accountMapper.getAccount(username);
        if (account != null) {
            return Ret.err(null, "账号已被注册");
        }
        accountMapper.insert(new Account(username, password));
        return Ret.ok("注册成功");
    }

    public Ret<Long> login(String username, String password) {
        if (NullUtil.isAnyBlank(username, password)) {
            return Ret.err(null, "用户名或密码不能为空");
        }
        Account account = accountMapper.getAccount(username);
        if (account == null) {
            return Ret.err("账号不存在");
        }
        if (!password.equals(account.getPassword())) {
            return Ret.err("密码错误");
        } else {
            return Ret.ok(account.getId());
        }
    }

    public Ret logout(String username) {
        return null;
    }
}
