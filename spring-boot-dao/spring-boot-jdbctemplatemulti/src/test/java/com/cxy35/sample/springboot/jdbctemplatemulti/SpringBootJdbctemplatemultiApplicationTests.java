package com.cxy35.sample.springboot.jdbctemplatemulti;

import com.cxy35.sample.springboot.jdbctemplatemulti.pojo.User;
import com.cxy35.sample.springboot.jdbctemplatemulti.service1.UserService1;
import com.cxy35.sample.springboot.jdbctemplatemulti.service2.UserService2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootJdbctemplatemultiApplicationTests {

    @Autowired
    UserService1 userService1;

    @Autowired
    UserService2 userService2;

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setAddress("杭州");
        userService1.addUser(user);
        System.out.println(user);

        User user2 = new User();
        user2.setUsername("zhangsan");
        user2.setAddress("杭州");
        userService2.addUser(user2);
        System.out.println(user2);
    }

    @Test
    public void addUser2() {
        User user = new User();
        user.setUsername("lisi");
        user.setAddress("北京");
        userService1.addUser2(user);
        System.out.println(user);
    }

    @Test
    public void deleteUserById() {
        userService1.deleteUserById(2);
    }

    @Test
    public void updateUserById() {
        User user = new User();
        user.setId(1);
        user.setUsername("zhangsan2");
        user.setAddress("上海");
        userService1.updateUserById(user);
    }

    @Test
    public void getAllUsers() {
        List<User> allUsers = userService1.getAllUsers();
        System.out.println(allUsers);
    }

    @Test
    public void getAllUsers2() {
        List<User> allUsers = userService1.getAllUsers2();
        System.out.println(allUsers);
    }
}
