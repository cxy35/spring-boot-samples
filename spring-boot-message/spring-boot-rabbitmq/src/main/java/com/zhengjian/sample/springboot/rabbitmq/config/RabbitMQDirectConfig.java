package com.zhengjian.sample.springboot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQDirectConfig {
    public final static String EXCHANGE_NAME_DIRECT = "exchange_direct";
    public final static String QUEUE_NAME_DIRECT_ONE = "queue_direct_one";
    public final static String QUEUE_NAME_DIRECT_TWO = "queue_direct_two";

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME_DIRECT, true, false);
    }

    @Bean
    Queue directOneQueue() {
        return new Queue(RabbitMQDirectConfig.QUEUE_NAME_DIRECT_ONE, true);
    }

    @Bean
    Queue directTwoQueue() {
        return new Queue(RabbitMQDirectConfig.QUEUE_NAME_DIRECT_TWO, true);
    }

    @Bean
    Binding directOneBinding() {
        return BindingBuilder.bind(directOneQueue()).to(directExchange()).with("one");
    }

    @Bean
    Binding directTwoBinding() {
        return BindingBuilder.bind(directTwoQueue()).to(directExchange()).with("two");
    }
}
