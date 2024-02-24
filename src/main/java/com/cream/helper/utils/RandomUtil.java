package com.cream.helper.utils;

import java.util.*;

public class RandomUtil {

    private static final Random RANDOM = new Random();

    public static <T> T randomOne(Collection<T> collection) {
        if (NullUtil.isEmpty(collection)) {
            return null;
        }
        List<T> list = new ArrayList<>(collection);
        Collections.shuffle(list, RANDOM);
        return list.get(0);
    }

    public static <T> T random(T... e) {
        if (NullUtil.isEmpty(e)) {
            return null;
        }
        return e[RANDOM.nextInt(e.length)];
    }
}
