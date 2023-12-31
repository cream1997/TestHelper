package com.cream.helper.mapper.mock;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cream.helper.obj.entity.mock.MockRemoteUser;
import org.apache.ibatis.annotations.Param;

public interface MockRemoteUserMapper extends BaseMapper<MockRemoteUser> {
    MockRemoteUser getMockRemoteUser(@Param("username") String username);
}
