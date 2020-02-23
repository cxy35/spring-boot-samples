package com.cxy35.sample.springboot.test;

import com.cxy35.sample.springboot.test.pojo.Book;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
// @SpringBootTest
@org.springframework.boot.test.autoconfigure.json.JsonTest
public class TestJson {
    @Autowired
    JacksonTester<Book> jacksonTester;

    @Test
    public void test() throws IOException {
        // 序列化
        Book book = new Book();
        book.setId(99);
        book.setName("红楼梦");
        book.setAuthor("曹雪芹");
        Assertions.assertThat(jacksonTester.write(book))
                .isEqualToJson("book.json");
        Assertions.assertThat(jacksonTester.write(book))
                .hasJsonPathStringValue("@.name");
        Assertions.assertThat(jacksonTester.write(book))
                .extractingJsonPathStringValue("@.name")
                .isEqualTo("红楼梦");
    }

    @Test
    public void test2() throws IOException {
        // 反序列化
        String content = "{\"id\":99,\"name\":\"红楼梦\",\"author\":\"曹雪芹\"}";
        Assertions.assertThat(jacksonTester.parseObject(content).getName()).isEqualTo("红楼梦");
    }
}
