package com.cxy35.sample.springboot.jackson.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.SimpleDateFormat;

@Configuration
public class WebMvcConfig {

    // 实现日期格式化方法1：
    // 覆盖 JacksonHttpMessageConvertersConfiguration 中默认的 MappingJackson2HttpMessageConverter
    /*@Bean
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat("yyyy/MM/dd"));
        converter.setObjectMapper(om);
        return converter;
    }*/

    // 实现日期格式化方法2（更小的粒度）：
    // 覆盖 JacksonHttpMessageConvertersConfiguration 中默认的 ObjectMapper（由 JacksonAutoConfiguration 中注入）
    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return om;
    }
}
