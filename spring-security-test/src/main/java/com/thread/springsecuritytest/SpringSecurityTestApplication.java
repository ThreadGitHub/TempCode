package com.thread.springsecuritytest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)    //开启springSecurity 权限注解支持
@MapperScan("com.thread.springsecuritytest.mapper")
public class SpringSecurityTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityTestApplication.class, args);
    }
}
