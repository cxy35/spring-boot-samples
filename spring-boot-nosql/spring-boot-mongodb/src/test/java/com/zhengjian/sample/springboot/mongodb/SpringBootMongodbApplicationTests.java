package com.zhengjian.sample.springboot.mongodb;

import com.zhengjian.sample.springboot.mongodb.dao.BookDao;
import com.zhengjian.sample.springboot.mongodb.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest
class SpringBootMongodbApplicationTests {
    // 方式1
    @Autowired
    BookDao bookDao;
    // 方式2
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void test1() {
        Book book = new Book();
        book.setAuthor("曹雪芹");
        book.setName("红楼梦");
        book.setId(2);
        bookDao.insert(book);

        List<Book> list = bookDao.findAll();
        System.out.println(list);

        List<Book> books = bookDao.findBookByNameContaining("红");
        System.out.println(books);
    }

    @Test
    public void test2() {
        Book book = new Book();
        book.setId(3);
        book.setName("水浒传");
        book.setAuthor("施耐庵");
        mongoTemplate.insert(book);

        List<Book> list = mongoTemplate.findAll(Book.class);
        System.out.println(list);
    }

}
