package com.cxy35.sample.springboot.springcache.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // 开启缓存
public class SpringBootSpringcacheRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSpringcacheRedisApplication.class, args);
    }

}
