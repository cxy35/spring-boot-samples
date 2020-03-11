package com.cxy35.sample.springboot.jpamulti.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.cxy35.sample.springboot.jpamulti.dao2", entityManagerFactoryRef = "localContainerEntityManagerFactoryBean2", transactionManagerRef = "platformTransactionManager2")
public class JpaConfig2 {
    // 此时 Spring 容器中有两个 DataSource 类型的 Bean ，所以这里需要按名称 byName 查找
    @Autowired
    @Qualifier("dsTwo")
    DataSource dsTwo;

    @Autowired
    JpaProperties jpaProperties;

    @Bean
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean2(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dsTwo)
                .properties(jpaProperties.getProperties())
                .persistenceUnit("pu2")
                .packages("com.cxy35.sample.springboot.jpamulti.pojo")
                .build();
    }

    @Bean
    PlatformTransactionManager platformTransactionManager2(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean2(builder).getObject());
    }
}
