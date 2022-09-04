package com.thread.springsecuritytest.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSONObject;
import com.thread.springsecuritytest.config.LoginUser;
import com.thread.springsecuritytest.config.RedisKeys;
import com.thread.springsecuritytest.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Component
public class AuthenicationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        String userId = null;
        try{
            //解析token
            JWT jwt = JWTUtil.parseToken(token);
            userId = jwt.getPayload("userId").toString();
        }catch (Exception e) {
            filterChain.doFilter(request, response);
        }
        if (StringUtils.isNotBlank(userId)) {
            JSONObject loginUserObject = (JSONObject) redisTemplate.opsForValue().get(RedisKeys.loginUserKeyPrefix + userId);
            if (!Objects.isNull(loginUserObject)) {
                LoginUser loginUser = JSONObject.parseObject(loginUserObject.toString(), LoginUser.class);
                if (!Objects.isNull(loginUser)) {
                    //获取权限
                    Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
                    //如果用户在redis中能查到，说明登录过那么设置security当前请求是认证过的
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        //放行
        filterChain.doFilter(request, response);
    }
}
