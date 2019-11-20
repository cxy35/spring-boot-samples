package com.zhengjian.sample.springboot.jpamulti;

import com.zhengjian.sample.springboot.jpamulti.dao1.BookDao1;
import com.zhengjian.sample.springboot.jpamulti.dao2.BookDao2;
import com.zhengjian.sample.springboot.jpamulti.pojo.Book;
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
class SpringBootJpamultiApplicationTests {

    @Autowired
    BookDao1 bookDao1;
    @Autowired
    BookDao2 bookDao2;

    @Test
    void contextLoads() {
    }

    @Test
    public void save() {
        Book book = new Book();
        book.setAuthor("罗贯中");
        book.setName("三国演义");
        bookDao1.save(book);

        Book book2 = new Book();
        book2.setAuthor("罗贯中3");
        book2.setName("三国演义3");
        bookDao2.save(book2);
    }

    @Test
    public void update() {
        Book book = new Book();
        book.setId(1);
        book.setAuthor("罗贯中2");
        book.setName("三国演义2");
        bookDao1.saveAndFlush(book);
    }

    @Test
    public void delete() {
        bookDao1.deleteById(1);
    }

    @Test
    public void findById() {
        Optional<Book> byId = bookDao1.findById(1);
        System.out.println(byId.get());
    }

    @Test
    public void findAll() {
        List<Book> all = bookDao1.findAll();
        System.out.println(all);
    }

    @Test
    public void findAllSort() {
        List<Book> list = bookDao1.findAll(Sort.by(Sort.Direction.DESC, "id"));
        System.out.println(list);
    }

    @Test
    public void findAllPage() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Book> page = bookDao1.findAll(pageable);
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("当前页记录数：" + page.getNumberOfElements());
        System.out.println("每页记录数：" + page.getSize());
        System.out.println("获取总页数：" + page.getTotalPages());
        System.out.println("查询结果：" + page.getContent());
        System.out.println("当前页（从0开始计）" + page.getNumber());
        System.out.println("是否为首页：" + page.isFirst());
        System.out.println("是否为尾页：" + page.isLast());
    }

    @Test
    public void findBookById() {
        Book book = bookDao1.findBookById(1);
        System.out.println(book);

        Book book2 = bookDao2.findBookById(1);
        System.out.println(book2);
    }

    @Test
    public void findByIdAndName() {
        Book book = bookDao1.findByIdAndName(1, "三国演义");
        System.out.println(book);
    }

    @Test
    public void findByIdGreaterThan() {
        List<Book> list = bookDao1.findByIdGreaterThan(2);
        System.out.println(list);
    }

    @Test
    public void findByIdLessThanOrNameContaining() {
        List<Book> list1 = bookDao1.findByIdLessThanOrNameContaining(2, "花");
        System.out.println(list1);
    }

    @Test
    public void getById() {
        Book book = bookDao1.getById(1);
        System.out.println(book);
    }

    @Test
    public void getBookById() {
        Book book = bookDao1.getBookById(1);
        System.out.println(book);
    }

    @Test
    public void getByParam() {
        List<Book> list1 = bookDao1.getByParam(1, "朝花夕拾");
        System.out.println(list1);
    }

    @Test
    public void getByParam2() {
        List<Book> list1 = bookDao1.getByParam2("朝花夕拾", 1);
        System.out.println(list1);
    }

    @Test
    public void getMaxIdBook() {
        Book book = bookDao1.getMaxIdBook();
        System.out.println(book);

        Book book2 = bookDao2.getMaxIdBook();
        System.out.println(book2);
    }


    @Test
    public void addBook() {
        Integer r1 = bookDao1.addBook("朝花夕拾", "鲁迅");
        System.out.println(r1);
    }

    @Test
    public void addBook2() {
        Integer r2 = bookDao1.addBook2("呐喊", "鲁迅");
        System.out.println(r2);
    }

}
