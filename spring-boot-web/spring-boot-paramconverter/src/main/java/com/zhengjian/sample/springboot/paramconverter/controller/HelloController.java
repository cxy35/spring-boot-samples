package com.zhengjian.sample.springboot.paramconverter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author cxy35
 * @Date 2019-07-26 7:17
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public void hello(Date birthday) {
        System.out.println(birthday);
    }
}
