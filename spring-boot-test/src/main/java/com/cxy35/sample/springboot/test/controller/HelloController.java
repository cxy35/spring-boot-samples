package com.cxy35.sample.springboot.test.controller;

import com.cxy35.sample.springboot.test.pojo.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(String name) {
        return "hello " + name;
    }

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book) {
        return book;
    }
}
