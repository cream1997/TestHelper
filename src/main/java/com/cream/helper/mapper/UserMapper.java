package com.cream.helper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cream.helper.obj.entity.account.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {


    User getUser(@Param("username") String username);
}
