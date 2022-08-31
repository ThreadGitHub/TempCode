package com.thread.springsecuritytest.controller;

import com.thread.springsecuritytest.domain.Hello;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试接口")
@RestController
public class HelloController {
    @GetMapping("/hello")
    public Hello hello() {
        return new Hello();
    }
}
