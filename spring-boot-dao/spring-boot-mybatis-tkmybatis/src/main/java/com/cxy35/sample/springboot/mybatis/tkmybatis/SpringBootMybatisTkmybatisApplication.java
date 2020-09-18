package com.cxy35.sample.springboot.mybatis.tkmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.cxy35.sample.springboot.mybatis.tkmybatis.mapper")
// @tk.mybatis.spring.annotation.MapperScan(basePackages = "com.cxy35.sample.springboot.mybatis.tkmybatis.mbg.mapper")
// @tk.mybatis.spring.annotation.MapperScan({"com.cxy35.sample.springboot.mybatis.tkmybatis.mapper", "com.cxy35.sample.springboot.mybatis.tkmybatis.mbg.mapper"})
public class SpringBootMybatisTkmybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisTkmybatisApplication.class, args);
    }

}
