package com.zhengjian.sample.springboot.commandlinerunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCommandlinerunnerApplication {

    // 打包后通过命令行启动：
    // java -jar spring-boot-commandlinerunner-0.0.1-SNAPSHOT.jar 三国演义 西游记

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCommandlinerunnerApplication.class, args);
    }

}
