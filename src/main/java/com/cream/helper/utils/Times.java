package com.cream.helper.utils;

public class Times {

    public static long ONE_SECOND = 1000;
    public static long ONE_MINUTE = 60 * ONE_SECOND;
    public static long ONE_HOUR = 60 * ONE_MINUTE;
    public static long ONE_DAY = ONE_HOUR * 24;

    public static long now() {
        return System.currentTimeMillis();
    }
}
