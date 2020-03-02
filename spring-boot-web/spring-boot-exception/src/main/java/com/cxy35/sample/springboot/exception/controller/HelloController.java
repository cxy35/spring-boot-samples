package com.cxy35.sample.springboot.exception.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    // 默认的错误页面查找顺序：发生了500错误 –> 查找动态 500.html –> 查找静态 500.html –> 查找动态 5xx.html –> 查找静态 5xx.html
    @GetMapping("/hello")
    public String hello() {
        int i = 1 / 0;
        return "hello";
    }
}
