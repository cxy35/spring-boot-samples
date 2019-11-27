package com.zhengjian.sample.springboot.tomcat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhengjian
 * @Date 2019-07-18 7:03
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello springboot!";
    }
}
