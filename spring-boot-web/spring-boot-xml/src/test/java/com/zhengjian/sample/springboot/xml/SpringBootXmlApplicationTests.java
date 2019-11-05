package com.zhengjian.sample.springboot.xml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootXmlApplicationTests {

    @Autowired
    Hello hello;

    @Test
    void contextLoads() {
        System.out.println(hello.sayHello());
    }

}
