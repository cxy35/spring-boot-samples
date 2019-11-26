package com.zhengjian.sample.springboot.springsecurity.urp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.zhengjian.sample.springboot.springsecurity.urp.mapper")
public class SpringBootSpringsecurityUrpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSpringsecurityUrpApplication.class, args);
    }

}
