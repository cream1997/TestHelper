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

    @ExceptionHandler(CommonRunError.class)
    public Ret<String> handleCommonRunErr(CommonRunError e) {
        log.error("发生异常", e);
        return Ret.err(e.getMessage());
    }

    @ExceptionHandler(CommonError.class)
    public Ret<String> handleCommonErr(CommonError e) {
        log.error("发生异常", e);
        return Ret.err(e.getMessage());
    }
}
