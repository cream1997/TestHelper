package com.cream.helper.utils;

import com.cream.helper.config.configuration.exception.RunErr;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

@Slf4j
public class NullUtil {

    public static boolean isBlank(String string) {
        return StringUtils.isBlank(string);
    }

    public static boolean isAnyBlank(String... strings) {
        return StringUtils.isAnyBlank(strings);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isAnyEmpty(Collection<?>... collections) {
        if (ArrayUtils.isEmpty(collections)) {
            return true;
        }
        for (Collection<?> collection : collections) {
            if (CollectionUtils.isEmpty(collection)) {
                return true;
            }
        }
        return false;
    }

    public static void checkNull(String name, String password) {
        if (NullUtil.isAnyBlank(name, password)) {
            throw new RunErr("用户名或密码不能为空");
        }
    }

    public static void assertNoNull(Object object) {
        if (object == null) {
            Util.logThrowErr("参数不能为空");
        } else if (object instanceof String && NullUtil.isBlank((String) object)) {
            Util.logThrowErr("参数不能为空");
        }
    }
}
