package com.thread.springsecuritytest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thread.springsecuritytest.domain.User;
import com.thread.springsecuritytest.mapper.UserMapper;
import com.thread.springsecuritytest.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
