package com.zhengjian.sample.springboot.staticresources.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-07-23 7:41
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    // 自定义配置静态资源的匹配规则和路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/javaboy/");
    }
}
