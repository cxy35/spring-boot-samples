package com.zhengjian.sample.springboot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQDirectConfig {
    public final static String NAME_DIRECT = "name_direct";
    public final static String QUEUE_DIRECT = "queue_direct";

    @Bean
    Queue queue() {
        return new Queue(RabbitMQDirectConfig.QUEUE_DIRECT);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(NAME_DIRECT, true, false);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct");
    }
}
