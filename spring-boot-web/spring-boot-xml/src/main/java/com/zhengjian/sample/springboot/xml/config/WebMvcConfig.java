package com.zhengjian.sample.springboot.xml.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author zhengjian
 * @Date 2019-07-24 17:12
 */
@Configuration
@ImportResource(locations = "classpath:beans.xml")
public class WebMvcConfig {

}
