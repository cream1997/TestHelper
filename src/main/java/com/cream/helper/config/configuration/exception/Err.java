package com.cream.helper.config.configuration.exception;

/**
 * 抛出后需要处理的通用异常
 */
public class Err extends Exception {
    public Err(String message) {
        super(message);
    }
}
