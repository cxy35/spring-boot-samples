package com.zhengjian.sample.springboot.aop.service;

import org.springframework.stereotype.Service;

/**
 * @Author cxy35
 * @Date 2019-07-26 7:33
 */
@Service
public class UserService {
    public String getUsernameById(Integer id) {
        System.out.println("getUsernameById");
        return "cxy35";
    }

    public void deleteUserById(Integer id) {
        System.out.println("deleteUserById");
    }
}
