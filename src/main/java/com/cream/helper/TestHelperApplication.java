package com.cream.helper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cream.helper.mapper")
@SpringBootApplication
public class TestHelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestHelperApplication.class, args);
    }

}
