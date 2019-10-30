package com.zhengjian.sample.springboot.properties;

import com.zhengjian.sample.springboot.properties.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootPropertiesApplicationTests {

    @Autowired
    Book book;

    @Test
    void contextLoads() {
        System.out.println(book);
    }

}
