package com.cxy35.sample.springboot.mybatismulti.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.cxy35.sample.springboot.mybatismulti.mapper2", sqlSessionFactoryRef = "sqlSessionFactory2", sqlSessionTemplateRef = "sqlSessionTemplate2")
public class MyBatisConfigTwo {
    // 此时 Spring 容器中有两个 DataSource 类型的 Bean ，所以这里需要按名称 byName 查找
    @Autowired
    @Qualifier("dsTwo")
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
