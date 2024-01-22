package com.cream.helper.utils;

import com.cream.helper.config.configuration.exception.CommonRunError;

public class FormCheckUtil {

    public static void checkNull(String name, String password) {
        if (NullUtil.isAnyBlank(name, password)) {
            throw new CommonRunError("用户名或密码不能为空");
        }
    }
}
