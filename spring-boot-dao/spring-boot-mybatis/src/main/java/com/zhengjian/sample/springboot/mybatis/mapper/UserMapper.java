package com.zhengjian.sample.springboot.mybatis.mapper;

import com.zhengjian.sample.springboot.mybatis.pojo.User;

import java.util.List;

/**
 * @Author zhengjian
 * @Date 2019/11/12 17:06
 */
// 方式1：在XML中写SQL >> UserMapper.xml
// @Mapper // 可在启动类中全局配置
public interface UserMapper {
    Integer addUser(User user);

    Integer deleteUserById(Integer id);

    Integer updateUserById(User user);

    List<User> getAllUser();
}
