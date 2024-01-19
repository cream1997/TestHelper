package com.cream.helper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cream.helper.obj.entity.account.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LocalUserMapper extends BaseMapper<User> {

    User getUser(@Param("username") String username);

    void deleteByUsername(@Param("username") String username);

    List<User> getUserAccounts(@Param("accountId") long accountId);
}
