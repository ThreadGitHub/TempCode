package com.thread.springsecuritytest.service;

import com.thread.springsecuritytest.domain.dto.UserDto;
import com.thread.springsecuritytest.util.ResponseResult;

public interface LoginService {
    /**
     * 登录接口
     * @param user
     * @return
     */
    ResponseResult login(UserDto user);

    /**
     * 注册接口
     * @param userDto
     * @return
     */
    void register(UserDto userDto);

    /**
     * 退出登录
     * @return
     */
    ResponseResult logout();
}
