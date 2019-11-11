package com.zhengjian.sample.springboot.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling// 开启定时任务
public class SpringBootScheduledApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootScheduledApplication.class, args);
    }

}
