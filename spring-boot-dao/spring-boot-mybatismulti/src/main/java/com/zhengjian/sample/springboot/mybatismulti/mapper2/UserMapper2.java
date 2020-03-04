package com.zhengjian.sample.springboot.mybatismulti.mapper2;

import com.zhengjian.sample.springboot.mybatismulti.pojo.User;

import java.util.List;

/**
 * @Author cxy35
 * @Date 2019/11/12 17:06
 */
// 方式1：在XML中写SQL >> UserMapper2.xml
// @Mapper // 在MyBatisConfigTwo类中配置扫描
public interface UserMapper2 {
    Integer addUser(User user);

    Integer deleteUserById(Integer id);

    Integer updateUserById(User user);

    List<User> getAllUser();
}
