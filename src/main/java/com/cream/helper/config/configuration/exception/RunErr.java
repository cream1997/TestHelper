package com.cream.helper.config.configuration.exception;

/**
 * 抛出后无需处理的通用异常
 */
public class RunErr extends RuntimeException {

    public RunErr(String message) {
        super(message);
    }

    public RunErr(Err err) {
        super(err.getMessage());
    }

    public RunErr(Throwable cause) {
        super(cause);
    }
}
