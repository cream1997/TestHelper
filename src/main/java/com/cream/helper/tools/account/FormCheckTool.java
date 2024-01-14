package com.cream.helper.tools.account;

import com.cream.helper.config.configuration.exception.CommonException;
import com.cream.helper.utils.NullUtil;
import org.springframework.stereotype.Component;

@Component
public class FormCheckTool {

    public void checkNull(String name, String password) {
        if (NullUtil.isAnyBlank(name, password)) {
            throw new CommonException("用户名或密码不能为空");
        }
    }
}
