package com.cxy35.sample.springboot.yaml;

import com.cxy35.sample.springboot.yaml.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootYamlApplicationTests {

    @Autowired
    Book book;

    @Test
    void contextLoads() {
        System.out.println(book);
    }

}
