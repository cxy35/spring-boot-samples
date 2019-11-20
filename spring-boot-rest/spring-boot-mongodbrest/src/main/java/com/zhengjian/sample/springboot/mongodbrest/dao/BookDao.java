package com.zhengjian.sample.springboot.mongodbrest.dao;

import com.zhengjian.sample.springboot.mongodbrest.pojo.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// @CrossOrigin // 跨域配置
// @RepositoryRestResource(path = "bs", collectionResourceRel = "bs", itemResourceRel = "b")
public interface BookDao extends MongoRepository<Book, Integer> {
    // 默认
    // 新增：POST http://localhost:8080/books
    // 删除：DELETE http://localhost:8080/books/1
    // 编辑：PUT http://localhost:8080/books/1
    // 查询分页列表：GET http://localhost:8080/books
    // 查询详情：GET http://localhost:8080/books/1
    // 自定义查询列表：GET http://localhost:8080/books/search
    // 自定义查询：GET http://localhost:8080/books/search/findBookByNameContaining?name=花

    // 自定义查询
    // @RestResource(path = "byName", rel = "findByName")
    List<Book> findBookByNameContaining(@Param("name") String name);
}
