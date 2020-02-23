package com.cxy35.sample.springboot.usemystarter;

import com.cxy35.sample.springboot.mystarter.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootUsemystarterApplicationTests {

    @Autowired
    HelloService helloService;

    @Test
    void contextLoads() {
        System.out.println(helloService.sayHello());
    }
}
