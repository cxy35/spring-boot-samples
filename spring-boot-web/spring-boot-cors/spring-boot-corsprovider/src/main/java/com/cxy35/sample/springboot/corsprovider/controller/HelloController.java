package com.cxy35.sample.springboot.corsprovider.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin(origins = "http://127.0.0.1:8081")
public class HelloController {
    @GetMapping("/get")
    // @CrossOrigin(origins = "http://127.0.0.1:8081")
    public String get() {
        return "get";
    }

    @PutMapping("/put")
    // @CrossOrigin(origins = "http://127.0.0.1:8081")
    public String put() {
        return "put";
    }
}
