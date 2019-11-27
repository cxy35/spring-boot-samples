package com.zhengjian.sample.springboot.jackson.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.SimpleDateFormat;

/**
 * @Author zhengjian
 * @Date 2019-07-23 6:54
 */
@Configuration
public class WebMvcConfig {

    // 实现日期格式化方法1：
    // 覆盖JacksonHttpMessageConvertersConfiguration中默认的MappingJackson2HttpMessageConverter Bean
//    @Bean
//    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper om = new ObjectMapper();
//        om.setDateFormat(new SimpleDateFormat("yyyy/MM/dd"));
//        converter.setObjectMapper(om);
//        return converter;
//    }

    // 实现日期格式化方法2（更小的粒度）：
    // 覆盖JacksonHttpMessageConvertersConfiguration中默认的ObjectMapper Bean（由JacksonAutoConfiguration中注入）
    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return om;
    }

}
