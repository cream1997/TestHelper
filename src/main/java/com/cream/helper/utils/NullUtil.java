package com.cream.helper.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

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
}
