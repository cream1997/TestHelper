package com.cream.helper.database;

import cn.hutool.core.util.IdUtil;
import com.cream.helper.mapper.AccountMapper;
import com.cream.helper.obj.entity.account.Account;
import com.cream.helper.utils.Times;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountTableTest {

    @Autowired
    private AccountMapper accountMapper;


    @Test
    public void testAddAccount() {
        Account account = new Account(IdUtil.nanoId(), "123456", Times.now());
        account.setDeleted(true);
        accountMapper.insert(account);
        assert account.getId() != null;
    }
}
