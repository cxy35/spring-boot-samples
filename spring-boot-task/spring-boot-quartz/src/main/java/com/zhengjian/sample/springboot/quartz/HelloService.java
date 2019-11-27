package com.zhengjian.sample.springboot.quartz;

import java.util.Date;

/**
 * @Author zhengjian
 * @Date 2019-06-04 15:14
 */
public class HelloService {
    public void sayHello() {
        System.out.println("hello service >>>"+new Date());
    }
}