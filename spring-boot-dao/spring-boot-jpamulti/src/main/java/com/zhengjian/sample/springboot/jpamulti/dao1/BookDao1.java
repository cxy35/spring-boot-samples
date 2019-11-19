package com.zhengjian.sample.springboot.jpamulti.dao1;

import com.zhengjian.sample.springboot.jpamulti.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookDao1 extends JpaRepository<Book,Integer> {
    // 方法定义规范：
    // 1.按照 Spring Data 的规范，查询方法以 find/get/read 开头
    // 2.涉及条件查询时，条件的属性用条件关键字连接，要注意的是：条件属性以首字母大写
    // 3.支持属性的级联查询. 若当前类有符合条件的属性, 则优先使用, 而不使用级联属性. 若需要使用级联属性, 则属性之间使用 _ 进行连接

    // Book findById(Integer id);// 父类中定义了该方法

    Book findBookById(Integer id);

    Book findByIdAndName(Integer id, String name);

    List<Book> findByIdGreaterThan(Integer id);

    List<Book> findByIdLessThanOrNameContaining(Integer id, String name);

    Book getById(Integer id);

    Book getBookById(Integer id);

    Book readById(Integer id);

    Book readBookById(Integer id);

    // @Query注解：JPQL或SQL
    @Query(value = "select b.* from t_book b where b.id>?1 and b.name=?2", nativeQuery = true)
    List<Book> getByParam(Integer id, String name);// 参数要按顺序

    @Query(value = "select b.* from t_book b where id>:id and name=:name", nativeQuery = true)
    List<Book> getByParam2(@Param("name") String name, @Param("id") Integer id);// 参数可以不按顺序（推荐）

    @Query(value = "select * from t_book where id=(select max(id) from t_book)", nativeQuery = true)
    Book getMaxIdBook();

    // @Modifying注解、@Transactional注解
    @Query(value = "insert into t_book(name,author) values(?1,?2)", nativeQuery = true)
    @Modifying
    @Transactional
    Integer addBook(String name, String author);// 参数要按顺序

    @Query(value = "insert into t_book(name,author) values(:name,:author)", nativeQuery = true)
    @Modifying
    @Transactional
    Integer addBook2(@Param("name") String name, @Param("author") String author);// 参数可以不按顺序（推荐）

}
