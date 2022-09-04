package com.thread.springsecuritytest.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.thread.springsecuritytest.domain.User;
import com.thread.springsecuritytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 处理 SpringSecurity 访问数据库查询用户信息
 * @author Thread
 */
@Service
public class SecurityUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 主要做的是从数据库中取出需要验证的用户信息并封装成 UserDetails 对象供 SprigSecurity 使用
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询数据库用户
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }
        //封装成一个 UserDetails 对象
        return new LoginUser(user, Arrays.asList("test", "AAA"));
    }
}
