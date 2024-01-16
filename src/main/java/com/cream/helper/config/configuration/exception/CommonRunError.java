package com.cream.helper.config.configuration.exception;

/**
 * 抛出后无需处理的通用异常
 */
public class CommonRunError extends RuntimeException {

    public CommonRunError(String message) {
        super(message);
    }
}
