package com.zhengjian.sample.springboot.gson.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-07-23 6:54
 */
@Configuration
public class WebMvcConfig {

    // 实现日期格式化方法1：
    // 覆盖GsonHttpMessageConvertersConfiguration中默认的GsonHttpMessageConverter Bean
//    @Bean
//    GsonHttpMessageConverter gsonHttpMessageConverter() {
//        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
//        converter.setGson(new GsonBuilder().setDateFormat("yyyy/MM/dd").create());
//        return converter;
//    }

    // 实现日期格式化方法2（更小的粒度）：
    // 覆盖GsonHttpMessageConvertersConfiguration中默认的Gson Bean（由GsonAutoConfiguration中注入）
    @Bean
    Gson gson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    }

}
