package com.cream.helper.mapper.mock;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cream.helper.obj.bo.Role;
import org.apache.ibatis.annotations.Param;

public interface MockRoleMapper extends BaseMapper<Role> {
    boolean containsName(@Param("name") String name);
}
