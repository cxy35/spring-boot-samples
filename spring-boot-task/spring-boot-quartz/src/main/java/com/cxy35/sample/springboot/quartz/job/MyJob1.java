package com.cxy35.sample.springboot.quartz.job;

import org.springframework.stereotype.Component;

import java.util.Date;

// Job 定义方式1：直接定义一个 Bean 并注册到 Spring 容器中（无法传参）。
@Component
public class MyJob1 {
    public void sayHello() {
        System.out.println("MyJob1 >>> "+new Date());
    }
}