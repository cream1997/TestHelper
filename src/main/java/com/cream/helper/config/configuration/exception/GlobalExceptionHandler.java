package com.cream.helper.config.configuration.exception;

import com.cream.helper.obj.Ret;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonRunError.class)
    public Ret<String> handleCommonRunErr(CommonRunError e) {
        return Ret.err(e.getMessage());
    }

    @ExceptionHandler(CommonError.class)
    public Ret<String> handleCommonErr(CommonError e) {
        return Ret.err(e.getMessage());
    }
}
