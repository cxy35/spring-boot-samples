package com.zhengjian.sample.springboot.applicationrunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootApplicationrunnerApplication {

    // 打包后通过命令行启动：
    // java -jar spring-boot-applicationrunner-0.0.1-SNAPSHOT.jar 三国演义 西游记 --age=25

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationrunnerApplication.class, args);
    }

}
