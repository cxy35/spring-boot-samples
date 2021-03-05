package com.cxy35.sample.springboot.mybatis.mybatisplusmulti;

import com.cxy35.sample.springboot.mybatis.mybatisplusmulti.pojo.User;
import com.cxy35.sample.springboot.mybatis.mybatisplusmulti.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void save() {
        User user = new User();
        user.setUsername("test2");
        user.setPassword("123456");
        user.setAddress("宁波");
        user.setNickName("测试2");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userService.save(user);
    }

    @Test
    public void list() {
        List<User> users = userService.list();
        System.out.println(users);
    }
}
