package com.thread.springsecuritytest.service.impl;

import cn.hutool.jwt.JWTUtil;
import com.thread.springsecuritytest.config.LoginUser;
import com.thread.springsecuritytest.config.RedisKeys;
import com.thread.springsecuritytest.domain.User;
import com.thread.springsecuritytest.domain.dto.UserDto;
import com.thread.springsecuritytest.service.LoginService;
import com.thread.springsecuritytest.service.UserService;
import com.thread.springsecuritytest.util.ResponseResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${jwt.key}")
    private String jwtKey;

    @Override
    public ResponseResult login(UserDto user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = null;
        String msg = "";
        try{
            authenticate = authenticationManager.authenticate(authenticationToken);
        }catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        //认证失败获取错误信息
        if (Objects.isNull(authenticate)) {
            return ResponseResult.error(msg, null);
        } else {
            LoginUser userDetails = (LoginUser) authenticate.getPrincipal();
            //将认证用户存入redis token时效30分钟
            redisTemplate.opsForValue().set(RedisKeys.loginUserKeyPrefix + userDetails.getUser().getId(), userDetails, 30, TimeUnit.MINUTES);
            //认证成功获取token
            Map<String, Object> payload = new HashMap<>();
            payload.put("userName", user.getUserName());
            payload.put("userId", userDetails.getUser().getId());
            String token = JWTUtil.createToken(payload, jwtKey.getBytes());
            return ResponseResult.success(token);
        }
    }

    @Override
    public void register(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean save = userService.save(user);
        if (!save) {
            throw new RuntimeException("注册失败");
        }
    }

    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            Long id = loginUser.getUser().getId();
            redisTemplate.delete(RedisKeys.loginUserKeyPrefix + id);
        }
        return ResponseResult.success();
    }
}
