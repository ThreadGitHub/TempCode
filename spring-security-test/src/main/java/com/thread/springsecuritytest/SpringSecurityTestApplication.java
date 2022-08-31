package com.thread.springsecuritytest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.thread.springsecuritytest.mapper")
public class SpringSecurityTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityTestApplication.class, args);
    }
}
