package com.cream.helper.service.impl;

import com.cream.helper.config.configuration.exception.RunErr;
import com.cream.helper.config.configuration.exception.TipErr;
import com.cream.helper.mapper.AccountMapper;
import com.cream.helper.obj.domain.vo.account.AccountVO;
import com.cream.helper.obj.entity.account.Account;
import com.cream.helper.tools.JwtTool;
import com.cream.helper.utils.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试平台的账号服务
 */
@Service
public class AccountService {

    private final AccountMapper accountMapper;
    private final JwtTool jwtTool;

    @Autowired
    public AccountService(AccountMapper accountMapper,
                          JwtTool jwtTool) {
        this.accountMapper = accountMapper;
        this.jwtTool = jwtTool;
    }

    public void register(String username, String password) {
        NullUtil.checkNull(username, password);
        Account account = accountMapper.getAccount(username);
        if (account != null) {
            throw new TipErr("账号已被注册");
        }
        accountMapper.insert(new Account(username, password));
    }

    public AccountVO login(String username, String password) {
        NullUtil.checkNull(username, password);
        Account account = accountMapper.getAccount(username);
        if (account == null) {
            throw new RunErr("账号不存在");
        }
        if (!password.equals(account.getPassword())) {
            throw new RunErr("密码错误");
        } else {
            // 生成token
            String token = jwtTool.createToken(account.getId(), account.getAccountName());
            return new AccountVO(account.getId(), account.getAccountName(), account.getPassword(), token);
        }
    }

    public void logout(String username) {
    }

    public boolean checkToken(String token) {
        return jwtTool.tokenInvalid(token);
    }
}
