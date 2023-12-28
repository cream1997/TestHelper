package com.cream.helper.mapper;

import com.cream.helper.entity.Account;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {

    Account getAccount(@Param("username") String username);
}
