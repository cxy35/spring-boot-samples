package com.cxy35.sample.springboot.jpa;

import com.cxy35.sample.springboot.jpa.dao.BookDao;
import com.cxy35.sample.springboot.jpa.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringBootJpaApplicationTests {

    @Autowired
    BookDao bookDao;

    @Test
    public void save() {
        Book book = new Book();
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        bookDao.save(book);
    }

    @Test
    public void update() {
        Book book = new Book();
        book.setId(1);
        book.setAuthor("三国演义2");
        book.setName("罗贯中2");
        bookDao.saveAndFlush(book);
    }

    @Test
    public void delete() {
        bookDao.deleteById(1);
    }

    @Test
    public void findById() {
        Optional<Book> byId = bookDao.findById(1);
        System.out.println(byId.get());
    }

    @Test
    public void findAll() {
        List<Book> all = bookDao.findAll();
        System.out.println(all);
    }

    @Test
    public void findAllSort() {
        List<Book> list = bookDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
        System.out.println(list);
    }

    @Test
    public void findAllPage() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Book> page = bookDao.findAll(pageable);
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("当前页记录数：" + page.getNumberOfElements());
        System.out.println("每页记录数：" + page.getSize());
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("查询结果：" + page.getContent());
        System.out.println("当前页（从0开始计）：" + page.getNumber());
        System.out.println("是否为首页：" + page.isFirst());
        System.out.println("是否为尾页：" + page.isLast());
    }

    @Test
    public void findBookById() {
        Book book = bookDao.findBookById(1);
        System.out.println(book);
    }

    @Test
    public void findByIdAndName() {
        Book book = bookDao.findByIdAndName(1, "三国演义");
        System.out.println(book);
    }

    @Test
    public void findByIdGreaterThan() {
        List<Book> list = bookDao.findByIdGreaterThan(2);
        System.out.println(list);
    }

    @Test
    public void findByIdLessThanOrNameContaining() {
        List<Book> list1 = bookDao.findByIdLessThanOrNameContaining(2, "花");
        System.out.println(list1);
    }

    @Test
    public void getById() {
        Book book = bookDao.getById(1);
        System.out.println(book);
    }

    @Test
    public void getBookById() {
        Book book = bookDao.getBookById(1);
        System.out.println(book);
    }

    @Test
    public void getByParam() {
        List<Book> list1 = bookDao.getByParam(1, "朝花夕拾");
        System.out.println(list1);
    }

    @Test
    public void getByParam2() {
        List<Book> list1 = bookDao.getByParam2("朝花夕拾", 1);
        System.out.println(list1);
    }

    @Test
    public void getMaxIdBook() {
        Book book = bookDao.getMaxIdBook();
        System.out.println(book);
    }


    @Test
    public void addBook() {
        Integer r1 = bookDao.addBook("朝花夕拾", "鲁迅");
        System.out.println(r1);
    }

    @Test
    public void addBook2() {
        Integer r2 = bookDao.addBook2("呐喊", "鲁迅");
        System.out.println(r2);
    }

}
