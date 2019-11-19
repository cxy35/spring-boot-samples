package com.zhengjian.sample.springboot.mongodb.dao;

import com.zhengjian.sample.springboot.mongodb.pojo.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookDao extends MongoRepository<Book, Integer> {
    // 方法命名规则类似JPA
    List<Book> findBookByNameContaining(String name);
}
