package com.cream.helper.config.configuration.exception;

import com.cream.helper.obj.Ret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(RunErr.class)
    public Ret<String> handleCommonRunErr(RunErr e) {
        log.error("发生异常", e);
        return Ret.err(e.getMessage());
    }

    @ExceptionHandler(Err.class)
    public Ret<String> handleCommonErr(Err e) {
        log.error("发生异常", e);
        return Ret.err(e.getMessage());
    }
}
