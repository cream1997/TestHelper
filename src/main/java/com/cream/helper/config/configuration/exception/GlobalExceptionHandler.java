package com.cream.helper.config.configuration.exception;

import com.cream.helper.obj.Ret;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CommonException.class)
    public Ret<String> handleException(CommonException e) {
        return Ret.err(e.getMessage());
    }
}
