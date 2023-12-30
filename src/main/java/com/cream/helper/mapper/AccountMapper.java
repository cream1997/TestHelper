package com.cream.helper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cream.helper.obj.entity.account.Account;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper extends BaseMapper<Account> {
    Account getAccount(@Param("username") String username);
}
