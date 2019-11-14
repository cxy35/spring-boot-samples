package com.zhengjian.sample.springboot.jdbctemplate;

import com.zhengjian.sample.springboot.jdbctemplate.pojo.User;
import com.zhengjian.sample.springboot.jdbctemplate.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootJdbctemplateApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setAddress("杭州");
        userService.addUser(user);
        System.out.println(user);
    }

    @Test
    public void addUser2() {
        User user = new User();
        user.setUsername("lisi");
        user.setAddress("北京");
        userService.addUser2(user);
        System.out.println(user);
    }

    @Test
    public void deleteUserById() {
        userService.deleteUserById(2);
    }

    @Test
    public void updateUserById() {
        User user = new User();
        user.setId(1);
        user.setUsername("zhangsan2");
        user.setAddress("上海");
        userService.updateUserById(user);
    }

    @Test
    public void getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        System.out.println(allUsers);
    }
    @Test
    public void getAllUsers2() {
        List<User> allUsers = userService.getAllUsers2();
        System.out.println(allUsers);
    }
}
