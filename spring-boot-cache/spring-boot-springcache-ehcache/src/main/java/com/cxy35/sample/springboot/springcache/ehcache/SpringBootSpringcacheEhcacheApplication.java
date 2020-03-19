package com.cxy35.sample.springboot.springcache.ehcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootSpringcacheEhcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSpringcacheEhcacheApplication.class, args);
    }

}
