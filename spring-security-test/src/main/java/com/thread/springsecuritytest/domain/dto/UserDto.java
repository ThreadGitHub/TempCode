package com.thread.springsecuritytest.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;
}
