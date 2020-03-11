package com.cxy35.sample.springboot.mybatismulti.mapper2;

import com.cxy35.sample.springboot.mybatismulti.pojo.User;

import java.util.List;

// 方式1：在 XML 中写 SQL >> UserMapper2.xml
// @Mapper // 在 MyBatisConfigTwo 类中配置扫描
public interface UserMapper2 {
    Integer addUser(User user);

    Integer deleteUserById(Integer id);

    Integer updateUserById(User user);

    List<User> getAllUser();
}
