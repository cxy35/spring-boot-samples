package com.zhengjian.sample.springboot.redis.springsession.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class HelloController {

    // java -jar spring-boot-redis-springsession-0.0.1-SNAPSHOT.jar -- server.port=9080
    // java -jar spring-boot-redis-springsession-0.0.1-SNAPSHOT.jar -- server.port=9081
    @Value("${server.port}")
    Integer port;

    @GetMapping("/set")
    public String set(HttpSession session) {
        session.setAttribute("name", "zhengjian");
        return String.valueOf(port);
    }

    @GetMapping("/get")
    public String get(HttpSession session) {
        return ((String) session.getAttribute("name")) + port;
    }
}
