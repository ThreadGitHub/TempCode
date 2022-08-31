package com.thread.springsecuritytest;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.thread.springsecuritytest.domain.User;
import com.thread.springsecuritytest.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ApplicationTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        String encode = passwordEncoder.encode("1234");
        System.out.println(encode);
        List<User> users = userService.listByIds(Arrays.asList(1));
        System.out.println(users);
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
