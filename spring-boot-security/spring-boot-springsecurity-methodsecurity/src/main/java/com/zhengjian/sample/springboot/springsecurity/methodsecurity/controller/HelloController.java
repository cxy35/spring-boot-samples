package com.zhengjian.sample.springboot.springsecurity.methodsecurity.controller;

import com.zhengjian.sample.springboot.springsecurity.methodsecurity.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        return helloService.hello();
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return helloService.admin();
    }

    @GetMapping("/user/hello")
    public String user() {
        return helloService.user();
    }
}
