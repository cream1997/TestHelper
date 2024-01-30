package com.cream.helper.service.impl;

import com.cream.helper.config.configuration.exception.CommonRunError;
import com.cream.helper.mapper.AccountMapper;
import com.cream.helper.mapper.AccountSetupMapper;
import com.cream.helper.obj.Ret;
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

    public Ret<String> register(String username, String password) {
        NullUtil.checkNull(username, password);
        Account account = accountMapper.getAccount(username);
        if (account != null) {
            return Ret.err(null, "账号已被注册");
        }
        accountMapper.insert(new Account(username, password));
        return Ret.ok("注册成功");
    }

    public Ret<AccountVO> login(String username, String password) {
        NullUtil.checkNull(username, password);
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

    public Ret<Object> logout(String username) {
        return null;
    }

    public Ret<Boolean> checkToken(String token) {
        return Ret.ok(jwtTool.tokenInvalid(token));
    }

    public String getDefaultServer(long accountId) {
        return accountSetupMapper.getDefaultMapper(accountId);
    }

    public void setDefaultServer(SetDefaultServerDTO setDefaultServerDTO) {
        long accountId = setDefaultServerDTO.getAccountId();
        if (accountId == 0) {
            throw new CommonRunError("系统异常：检测到未登录~");
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
