package com.cream.helper.controller.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    public String register() {
        return "测试消息";
    }
}
