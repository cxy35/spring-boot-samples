package com.zhengjian.sample.springboot.mongodbrest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

// Rest配置方式2：自定义配置类，优先级高于配置文件
@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        // config.setBasePath("/api").setDefaultPageSize(2);
    }
}
