package com.cream.helper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@MapperScan("com.cream.helper.mapper")
@SpringBootApplication
@EnableWebMvc
public class TestHelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestHelperApplication.class, args);
    }

}
