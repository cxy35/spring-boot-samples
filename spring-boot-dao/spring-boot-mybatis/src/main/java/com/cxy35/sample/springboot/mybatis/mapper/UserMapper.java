package com.cxy35.sample.springboot.mybatis.mapper;

import com.cxy35.sample.springboot.mybatis.pojo.User;

import java.util.List;

// 方式1：在 XML 中写 SQL >> UserMapper.xml
// @Mapper // 可在启动类中全局配置
public interface UserMapper {
    Integer addUser(User user);

    Integer deleteUserById(Integer id);

    Integer updateUserById(User user);

    List<User> getAllUser();
}
