package com.cxy35.sample.springboot.fastjson.controller;

import com.cxy35.sample.springboot.fastjson.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    @GetMapping("/user")
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("cxy35 >> " + i);
            user.setAddress("https://cxy35.com >> " + i);
            user.setId(i);
            user.setBirthday(new Date());
            users.add(user);
        }
        return users;
    }
}
