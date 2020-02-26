package com.cxy35.sample.springboot.gson.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebMvcConfig {

    // 实现日期格式化方法1：
    // 覆盖 GsonHttpMessageConvertersConfiguration 中默认的 GsonHttpMessageConverter
    /*@Bean
    GsonHttpMessageConverter gsonHttpMessageConverter() {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(new GsonBuilder().setDateFormat("yyyy/MM/dd").create());
        return converter;
    }*/

    // 实现日期格式化方法2（更小的粒度）：
    // 覆盖 GsonHttpMessageConvertersConfiguration 中默认的 Gson（由 GsonAutoConfiguration 中注入）
    @Bean
    Gson gson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    }
}
