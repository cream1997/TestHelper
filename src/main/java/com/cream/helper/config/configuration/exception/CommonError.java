package com.cream.helper.config.configuration.exception;

/**
 * 抛出后需要处理的通用异常
 */
public class CommonError extends Exception {
    public CommonError(String message) {
        super(message);
    }
}
