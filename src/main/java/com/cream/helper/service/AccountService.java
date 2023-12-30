package com.cream.helper.service;

import com.cream.helper.mapper.AccountMapper;
import com.cream.helper.obj.entity.account.Account;
import com.cream.helper.obj.vo.Result;
import org.apache.commons.lang3.StringUtils;
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

    public Result<String> register(String username, String password) {
        if (StringUtils.isAnyBlank(username, password)) {
            return Result.failWithRet(null, "用户名或密码不能为空");
        }
        Account account = accountMapper.getAccount(username);
        if (account != null) {
            return Result.failWithRet(null, "账号已被注册");
        }
        accountMapper.insert(new Account(username, password));
        return Result.success("注册成功");
    }

    public Result<Long> login(String username, String password) {
        if (StringUtils.isAnyBlank(username, password)) {
            return Result.failWithRet(null, "用户名或密码不能为空");
        }
        Account account = accountMapper.getAccount(username);
        if (account == null) {
            return Result.fail("账号不存在");
        }
        if (!password.equals(account.getPassword())) {
            return Result.fail("密码错误");
        } else {
            return Result.success(account.getId());
        }
    }

    public Result logout(String username) {
        return null;
    }
}
