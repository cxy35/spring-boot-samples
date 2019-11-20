package com.zhengjian.sample.springboot.devtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDevtoolsApplication {

    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SpringBootDevtoolsApplication.class, args);
    }

}
