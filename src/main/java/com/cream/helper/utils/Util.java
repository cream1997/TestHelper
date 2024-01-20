package com.cream.helper.utils;

/**
 * 项目最常用的工具方法放入此类
 */
public class Util {

    /**
     * 获取调用栈
     */
    public static String getStackTrace() {
        StringBuilder stackTrace = new StringBuilder("Call Stack:\n");
        for (StackTraceElement traceElement : Thread.currentThread().getStackTrace()) {
            stackTrace.append(traceElement.toString())
                    .append("\n");
        }
        return stackTrace.toString();
    }

    public static void errExit() {
        System.exit(1);
    }

    public static void normalExit() {
        System.exit(0);
    }
}
