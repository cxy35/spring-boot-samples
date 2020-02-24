package com.cxy35.sample.springboot.thymeleaf.controller;

import com.cxy35.sample.springboot.thymeleaf.pojo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @GetMapping("/book")
    public String book(Model model) {
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setId(i);
            book.setName("三国演义:" + i);
            book.setAuthor("罗贯中:" + i);
            book.setPrice(30.0);
            bookList.add(book);
        }
        model.addAttribute("books", bookList);
        model.addAttribute("username","张三");

        // 返回视图，默认为 src/main/resources/templates/book.html
        return "book";
    }
}
