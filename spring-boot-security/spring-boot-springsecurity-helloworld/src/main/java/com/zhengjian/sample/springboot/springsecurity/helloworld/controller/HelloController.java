package com.zhengjian.sample.springboot.springsecurity.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    // 默认用户名=user，密码=启动是打印在控制台
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
