package com.cxy35.sample.springboot.jparest.dao;

import com.cxy35.sample.springboot.jparest.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

// @CrossOrigin // 跨域配置
// @RepositoryRestResource(path = "bs", collectionResourceRel = "bs", itemResourceRel = "b")
public interface BookDao extends JpaRepository<Book, Integer> {
    // 默认接口
    // 新增：POST http://127.0.0.1:8080/books
    // 根据 id 删除：DELETE http://127.0.0.1:8080/books/{id}
    // 根据 id 编辑：PUT http://127.0.0.1:8080/books/{id}
    // 查询分页列表：GET http://127.0.0.1:8080/books
    // 根据 id 查询详情：GET http://127.0.0.1:8080/books/{id}
    // 查询自定义接口列表：GET http://127.0.0.1:8080/books/search

    // 自定义查询：GET http://127.0.0.1:8080/books/search/findBookByNameContaining?name=国
    // @RestResource(path = "byName", rel = "findByName")
    List<Book> findBookByNameContaining(@Param("name") String name);

    // 避免暴露官方定义好的方法
    // @Override
    // @RestResource(exported = false)
    // void deleteById(Long id);
}
