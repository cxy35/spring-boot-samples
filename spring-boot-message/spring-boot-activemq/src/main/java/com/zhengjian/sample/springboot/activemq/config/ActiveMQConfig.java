package com.zhengjian.sample.springboot.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * @Author cxy35
 * @Date 2019/11/29 15:25
 */
@Configuration
public class ActiveMQConfig {
    public final static String QUEUE_NAME = "myQueue";

    @Bean
    Queue queue() {
        return new ActiveMQQueue(ActiveMQConfig.QUEUE_NAME);
    }
}
