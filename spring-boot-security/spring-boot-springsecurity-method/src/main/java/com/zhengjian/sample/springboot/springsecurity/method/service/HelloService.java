package com.zhengjian.sample.springboot.springsecurity.method.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

// 注意需要开启全局方法安全配置
// 在SecurityConfig上增加@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@Service
public class HelloService {
    // admin和user角色都能访问
    @PreAuthorize("hasAnyRole('admin','user')")
    public String hello() {
        return "hello";
    }

    // 只有admin角色都能访问
    @PreAuthorize("hasRole('admin')")
    public String admin() {
        return "hello admin";
    }

    // 只有user角色都能访问
    @Secured("ROLE_user")// 框架默认加了前缀：ROLE_
    public String user() {
        return "hello user";
    }
}
