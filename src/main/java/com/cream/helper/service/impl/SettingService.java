package com.cream.helper.service.impl;

import com.cream.helper.config.configuration.exception.RunErr;
import com.cream.helper.mapper.AccountSetupMapper;
import com.cream.helper.obj.domain.dto.account.SetDefaultServerDTO;
import com.cream.helper.obj.entity.account.AccountSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    private final AccountSetupMapper accountSetupMapper;

    @Autowired
    public SettingService(AccountSetupMapper accountSetupMapper) {
        this.accountSetupMapper = accountSetupMapper;
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
