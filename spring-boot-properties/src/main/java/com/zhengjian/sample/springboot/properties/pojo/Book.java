package com.zhengjian.sample.springboot.properties.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author zhengjian
 * @Date 2019/10/30 15:12
 */
@Component
@PropertySource("classpath:book.properties")
@ConfigurationProperties(prefix = "book")
public class Book {
//    @Value("${book.id}")
    private Long id;
//    @Value("${book.name}")
    private String name;
//    @Value("${book.author}")
    private String author;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
