package com.cream.helper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cream.helper.obj.entity.account.AccountSetup;
import org.apache.ibatis.annotations.Param;

public interface AccountSetupMapper extends BaseMapper<AccountSetup> {
    String getDefaultMapper(@Param("accountId") long accountId);

    void updateDefaultServer(@Param("accountId") long accountId, @Param("defaultServer") String defaultServer);
}
