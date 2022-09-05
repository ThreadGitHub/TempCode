package com.thread.springsecuritytest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thread.springsecuritytest.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<String> listAuthication(String userId);
}
