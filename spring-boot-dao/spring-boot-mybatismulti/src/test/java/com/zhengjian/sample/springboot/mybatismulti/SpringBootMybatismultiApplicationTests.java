package com.zhengjian.sample.springboot.mybatismulti;

import com.zhengjian.sample.springboot.mybatismulti.mapper1.UserMapper1;
import com.zhengjian.sample.springboot.mybatismulti.mapper2.UserMapper2;
import com.zhengjian.sample.springboot.mybatismulti.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootMybatismultiApplicationTests {
    @Autowired
    UserMapper1 userMapper1;
    @Autowired
    UserMapper2 userMapper2;

    @Test
    void contextLoads() {
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setAddress("杭州");
        userMapper1.addUser(user);

        User user2 = new User();
        user2.setUsername("zhangsan");
        user2.setAddress("杭州");
        userMapper2.addUser(user2);
    }

    @Test
    public void deleteUserById() {
        userMapper1.deleteUserById(1);
    }

    @Test
    public void updateUserById() {
        User user = new User();
        user.setId(1);
        user.setUsername("zhangsan2");
        user.setAddress("上海");
        userMapper1.updateUserById(user);
    }

    @Test
    public void getAllUsers() {
        List<User> allUsers = userMapper1.getAllUser();
        System.out.println(allUsers);

        List<User> allUsers2 = userMapper2.getAllUser();
        System.out.println(allUsers2);
    }

}
