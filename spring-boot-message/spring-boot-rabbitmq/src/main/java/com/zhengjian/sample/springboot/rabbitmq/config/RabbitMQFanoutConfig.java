package com.zhengjian.sample.springboot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQFanoutConfig {
    public static final String QUEUE_NAME_FANOUT_ONE = "queue_fanout_one";
    public static final String QUEUE_NAME_FANOUT_TWO = "queue_fanout_two";
    public static final String EXCHANGE_NAME_FANOUT = "exchange_fanout";

    @Bean
    Queue queueOne() {
        return new Queue(RabbitMQFanoutConfig.QUEUE_NAME_FANOUT_ONE);
    }

    @Bean
    Queue queueTwo() {
        return new Queue(RabbitMQFanoutConfig.QUEUE_NAME_FANOUT_TWO);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME_FANOUT, true, false);
    }

    @Bean
    Binding bindingOne() {
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }

    @Bean
    Binding bindingTwo() {
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }
}
