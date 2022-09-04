package com.thread.springsecuritytest.config;

import com.alibaba.fastjson.annotation.JSONField;
import com.thread.springsecuritytest.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails, Serializable {
    /**
     * 系统用户对象
     */
    private User user;

    /**
     * 权限集合
     */
    private List<String> authoritiesList = new ArrayList<>();

    /**
     * 缓存转换数据
     */
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> simpleGrantedAuthorities;

    public LoginUser(User user, List<String> authoritiesList) {
        this.user = user;
        this.authoritiesList = authoritiesList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (null != simpleGrantedAuthorities) {
            return simpleGrantedAuthorities;
        }

        List<GrantedAuthority> simpleGrantedAuthorities = authoritiesList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
