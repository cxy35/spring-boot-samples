package com.cxy35.sample.springboot.test;

import com.cxy35.sample.springboot.test.service.HelloService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// @RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {

    @Autowired
    HelloService helloService;

    @Test
    public void contextLoads() {
        String hello = helloService.sayHello("cxy35");
        Assert.assertThat(hello, Matchers.is("hello cxy35"));
    }
}
