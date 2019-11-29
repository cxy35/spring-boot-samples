package com.zhengjian.sample.springboot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQHeaderConfig {
    public static final String NAME_HEADER = "name_header";
    public static final String QUEUE_HEADER_NAME = "queue_header_name";
    public static final String QUEUE_HEADER_AGE = "queue_header_age";

    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange(NAME_HEADER, true, false);
    }

    @Bean
    Queue queueName() {
        return new Queue(RabbitMQHeaderConfig.QUEUE_HEADER_NAME);
    }

    @Bean
    Queue queueAge() {
        return new Queue(RabbitMQHeaderConfig.QUEUE_HEADER_AGE);
    }

    @Bean
    Binding bindingName() {
        // message的header中name和zhangsan要完全匹配
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        return BindingBuilder.bind(queueName()).to(headersExchange()).whereAny(map).match();
    }

    @Bean
    Binding bindingAge() {
        // message的header中包含age
        return BindingBuilder.bind(queueAge()).to(headersExchange()).where("age").exists();
    }
}
