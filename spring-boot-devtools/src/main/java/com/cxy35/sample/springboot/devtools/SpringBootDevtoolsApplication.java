package com.cxy35.sample.springboot.devtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDevtoolsApplication {

    public static void main(String[] args) {
        // 方式2：关闭自动重启功能
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SpringBootDevtoolsApplication.class, args);
    }
}
