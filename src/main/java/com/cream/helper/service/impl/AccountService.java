package com.cream.helper.service.impl;

import com.cream.helper.config.configuration.exception.RunErr;
import com.cream.helper.mapper.AccountMapper;
import com.cream.helper.mapper.AccountSetupMapper;
import com.cream.helper.obj.domain.dto.account.SetDefaultServerDTO;
import com.cream.helper.obj.domain.vo.account.AccountVO;
import com.cream.helper.obj.entity.account.Account;
import com.cream.helper.obj.entity.account.AccountSetup;
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
    private final AccountSetupMapper accountSetupMapper;
    private final JwtTool jwtTool;

    @Autowired
    public AccountService(AccountMapper accountMapper,
                          AccountSetupMapper accountSetupMapper,
                          JwtTool jwtTool) {
        this.accountMapper = accountMapper;
        this.accountSetupMapper = accountSetupMapper;
        this.jwtTool = jwtTool;
    }

    public void register(String username, String password) {
        NullUtil.checkNull(username, password);
        Account account = accountMapper.getAccount(username);
        if (account != null) {
            throw new RunErr("账号已被注册");
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

    public String getDefaultServer(long accountId) {
        return accountSetupMapper.getDefaultMapper(accountId);
    }

    public void setDefaultServer(SetDefaultServerDTO setDefaultServerDTO) {
        long accountId = setDefaultServerDTO.getAccountId();
        if (accountId == 0) {
            throw new RunErr("系统异常：检测到未登录~");
        }
        AccountSetup accountSetup = accountSetupMapper.selectById(accountId);
        if (accountSetup == null) {
            accountSetup = new AccountSetup(accountId);
            accountSetup.setDefaultServer(setDefaultServerDTO.getDefaultServer());
            accountSetupMapper.insert(accountSetup);
        } else {
            accountSetupMapper.updateDefaultServer(accountId, setDefaultServerDTO.getDefaultServer());
        }
    }
}
