package com.cxy35.sample.springboot.springsecurity.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    // 默认用户名为 user，密码在项目启动时打印在控制台
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
