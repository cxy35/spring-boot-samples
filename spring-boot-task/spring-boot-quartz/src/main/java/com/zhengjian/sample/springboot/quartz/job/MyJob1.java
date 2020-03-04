package com.zhengjian.sample.springboot.quartz.job;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author cxy35
 * @Date 2019-06-04 15:12
 * 方式1：首先将这个 Job 注册到 Spring 容器中。这种定义方式有一个缺陷，就是无法传参。
 */
@Component
public class MyJob1 {
    public void sayHello() {
        System.out.println("MyJob1>>>"+new Date());
    }
}