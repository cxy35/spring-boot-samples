package com.zhengjian.sample.springboot.test;

import com.zhengjian.sample.springboot.test.service.HelloService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {

    @Autowired
    HelloService helloService;

    @Test
    public void contextLoads() {
        String hello = helloService.sayHello("javaboy");
        Assert.assertThat(hello, Matchers.is("hello javaboy"));
    }

}
