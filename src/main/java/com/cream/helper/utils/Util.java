package com.cream.helper.utils;

import com.cream.helper.config.configuration.exception.RunErr;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目最常用的工具方法放入此类
 */
@Slf4j
public class Util {

    /**
     * 获取调用栈
     */
    public static String getSimpleStackTrace() {
        StringBuilder stackTrace = new StringBuilder("Call Stack:\n");
        for (StackTraceElement traceElement : Thread.currentThread().getStackTrace()) {
            stackTrace.append(traceElement.toString())
                    .append("\n");
        }
        return stackTrace.toString();
    }

    public static void logErr(String errInfo) {
        log.error(errInfo + ":\n" + getSimpleStackTrace());
    }

    public static void logThrowErr(String errInfo) {
        log.error(errInfo + ":\n" + getSimpleStackTrace());
        throw new RunErr("errInfo: " + getSimpleStackTrace());
    }

    public static void errExit() {
        System.exit(1);
    }

    public static void normalExit() {
        System.exit(0);
    }
}
