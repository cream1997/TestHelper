package com.cream.helper.mapper.mock;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cream.helper.service.bo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MockRoleMapper extends BaseMapper<Role> {

    Role getRole(@Param("id") long id);

    boolean containsName(@Param("name") String name);

    List<Role> getRoleList(@Param("userId") long userId);

    boolean containsRole(@Param("id") long id, @Param("userId") long userId);
}
