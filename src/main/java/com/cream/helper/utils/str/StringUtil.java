package com.cream.helper.utils.str;

import com.cream.helper.config.configuration.exception.RunErr;
import com.cream.helper.utils.NullUtil;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

public class StringUtil {

    public static String douHaoJoin(Collection<?> params) {
        if (NullUtil.isEmpty(params)) {
            return "";
        }
        List<String> msgIdStr = params.stream().map(String::valueOf)
                .collect(Collectors.toList());
        return String.join(",", msgIdStr);
    }

    public static Collection<String> douHaoDisJoin(String joinedStr) {
        if (NullUtil.isBlank(joinedStr)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(Arrays.asList(joinedStr.split(",")));
    }

    public static <T extends Number> Collection<T> douHaoDisJoin(String joinedStr, Class<T> numberType) {
        Collection<String> strCollection = douHaoDisJoin(joinedStr);
        return strCollection.stream().map(str -> {
            try {
                Constructor<T> constructor = numberType.getConstructor(String.class);
                return constructor.newInstance(str);
            } catch (Exception e) {
                throw new RunErr(e);
            }
        }).collect(Collectors.toList());
    }

}
