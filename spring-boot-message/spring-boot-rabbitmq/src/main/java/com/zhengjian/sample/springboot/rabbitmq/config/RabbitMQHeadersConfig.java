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
public class RabbitMQHeadersConfig {
    public static final String EXCHANGE_NAME_HEADERS = "exchange_headers";
    public static final String QUEUE_NAME_HEADERS_NAME = "queue_headers_name";
    public static final String QUEUE_NAME_HEADERS_AGE = "queue_headers_age";

    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange(EXCHANGE_NAME_HEADERS, true, false);
    }

    @Bean
    Queue nameQueue() {
        return new Queue(RabbitMQHeadersConfig.QUEUE_NAME_HEADERS_NAME, true);
    }

    @Bean
    Queue ageQueue() {
        return new Queue(RabbitMQHeadersConfig.QUEUE_NAME_HEADERS_AGE, true);
    }

    @Bean
    Binding nameBinding() {
        // message的header中name和zhangsan要完全匹配
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        return BindingBuilder.bind(nameQueue()).to(headersExchange()).whereAny(map).match();
    }

    @Bean
    Binding ageBinding() {
        // message的header中包含age
        return BindingBuilder.bind(ageQueue()).to(headersExchange()).where("age").exists();
    }
}
