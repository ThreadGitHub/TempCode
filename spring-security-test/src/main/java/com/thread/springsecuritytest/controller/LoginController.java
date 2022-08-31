package com.thread.springsecuritytest.controller;

import com.thread.springsecuritytest.domain.dto.UserDto;
import com.thread.springsecuritytest.service.LoginService;
import com.thread.springsecuritytest.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户登录")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public ResponseResult<String> login(@RequestBody UserDto user) {
        return loginService.login(user);
    }

    @ApiOperation("注册接口")
    @PostMapping("/register")
    public ResponseResult register(@RequestBody UserDto userDto) {
        loginService.register(userDto);
        return ResponseResult.success();
    }

    @ApiOperation("退出登录")
    @GetMapping("/loginout")
    public ResponseResult logout() {
        return loginService.logout();
    }
}
