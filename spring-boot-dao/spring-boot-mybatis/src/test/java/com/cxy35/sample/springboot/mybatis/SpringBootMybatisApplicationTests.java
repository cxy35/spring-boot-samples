package com.cxy35.sample.springboot.mybatis;

import com.cxy35.sample.springboot.mybatis.mapper.UserMapper;
import com.cxy35.sample.springboot.mybatis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootMybatisApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setAddress("杭州");
        userMapper.addUser(user);
    }

    @Test
    public void deleteUserById() {
        userMapper.deleteUserById(1);
    }

    @Test
    public void updateUserById() {
        User user = new User();
        user.setId(1);
        user.setUsername("zhangsan2");
        user.setAddress("上海");
        userMapper.updateUserById(user);
    }

    @Test
    public void getAllUsers() {
        List<User> allUsers = userMapper.getAllUser();
        System.out.println(allUsers);
    }

}
