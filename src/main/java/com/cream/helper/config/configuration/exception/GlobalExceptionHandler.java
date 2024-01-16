package com.cream.helper.config.configuration.exception;

import com.cream.helper.obj.Ret;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler({CommonRunError.class, CommonError.class})
    public Ret<String> handleException(CommonRunError e) {
        return Ret.err(e.getMessage());
    }
}
