package com.zhengjian.sample.springboot.mybatismulti.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-07-28 11:14
 */
@Configuration
@MapperScan(basePackages = "com.zhengjian.sample.springboot.mybatismulti.mapper2", sqlSessionFactoryRef = "sqlSessionFactory2", sqlSessionTemplateRef = "sqlSessionTemplate2")
public class MyBatisConfigTwo {
    @Resource(name = "dsTwo")
    DataSource dsTwo;

    @Bean
    SqlSessionFactory sqlSessionFactory2() {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        try {
            factory.setDataSource(dsTwo);
            return factory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate2() {
        return new SqlSessionTemplate(sqlSessionFactory2());
    }
}
