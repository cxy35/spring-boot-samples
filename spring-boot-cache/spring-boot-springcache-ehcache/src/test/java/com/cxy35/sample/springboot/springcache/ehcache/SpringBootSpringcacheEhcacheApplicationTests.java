package com.cxy35.sample.springboot.springcache.ehcache;

import com.cxy35.sample.springboot.springcache.ehcache.pojo.User;
import com.cxy35.sample.springboot.springcache.ehcache.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootSpringcacheEhcacheApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        User u1 = userService.getUserById(1);

//        userService.deleteUserById(1);

//        User user = new User();
//        user.setId(1);
//        user.setUsername("zhangsan");
//        user.setAddress("hangzhou");
//        userService.updateUserById(user);

        User u2 = userService.getUserById(1);

        System.out.println(u1);
        System.out.println(u2);
    }
}
