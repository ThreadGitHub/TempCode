package com.thread.springsecuritytest;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.thread.springsecuritytest.domain.User;
import com.thread.springsecuritytest.service.UserService;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@SpringBootTest
public class ApplicationTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Data
    class TestUser{
        /**
         * 权限集合
         */
        private List<String> authorities = new ArrayList<>();
    }

    @Test
    public void test() {
        TestUser test = new TestUser();
        test.authorities.add("tesss");
        redisTemplate.opsForValue().set("test",test);
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("userid", "aaa");
        String token = JWTUtil.createToken(map, "FKDSJALGDSA".getBytes());
        System.out.println(token);

        JWT jwt = JWTUtil.parseToken(token);
        System.out.println(jwt.getPayload("userid"));
    }
}
