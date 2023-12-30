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
            return Result.fail(null, "用户名或密码不能为空");
        }
        try {
            accountMapper.insert(new Account(username, password));
        } catch (Exception e) {
            return Result.fail(e.getMessage(), "注册失败");
        }
        return Result.success("注册成功");
    }

    public Result login(String username, String password) {
        return null;
    }

    public Result logout(String username) {
        return null;
    }
}
