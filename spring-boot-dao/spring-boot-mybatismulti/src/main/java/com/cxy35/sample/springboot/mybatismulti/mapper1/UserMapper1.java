package com.cxy35.sample.springboot.mybatismulti.mapper1;

import com.cxy35.sample.springboot.mybatismulti.pojo.User;

import java.util.List;

// 方式1：在 XML 中写 SQL >> UserMapper1.xml
// @Mapper // 在 MyBatisConfigOne 类中配置扫描
public interface UserMapper1 {
    Integer addUser(User user);

    Integer deleteUserById(Integer id);

    Integer updateUserById(User user);

    List<User> getAllUser();
}
