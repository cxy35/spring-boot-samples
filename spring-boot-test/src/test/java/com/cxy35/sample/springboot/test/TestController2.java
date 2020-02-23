package com.cxy35.sample.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestController2 {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() {
        String cxy35 = testRestTemplate.getForObject("/hello?name={1}", String.class, "cxy35");
        System.out.println(cxy35);
    }
}
