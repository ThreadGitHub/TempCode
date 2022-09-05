package com.thread.springsecuritytest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thread.springsecuritytest.domain.User;

import java.util.List;

public interface UserService extends IService<User> {
    /**
     * 根据用户id查询权限信息
     * @param userId
     * @return
     */
    List<String> listAuthication(String userId);
}
