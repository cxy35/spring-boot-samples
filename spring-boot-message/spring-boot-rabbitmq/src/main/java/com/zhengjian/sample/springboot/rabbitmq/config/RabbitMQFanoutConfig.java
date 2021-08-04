package com.zhengjian.sample.springboot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQFanoutConfig {
    public static final String EXCHANGE_NAME_FANOUT = "exchange_fanout";
    public static final String QUEUE_NAME_FANOUT_ONE = "queue_fanout_one";
    public static final String QUEUE_NAME_FANOUT_TWO = "queue_fanout_two";

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME_FANOUT, true, false);
    }

    @Bean
    Queue fanoutOneQueue() {
        return new Queue(RabbitMQFanoutConfig.QUEUE_NAME_FANOUT_ONE, true);
    }

    @Bean
    Queue fanoutTwoQueue() {
        return new Queue(RabbitMQFanoutConfig.QUEUE_NAME_FANOUT_TWO, true);
    }

    @Bean
    Binding fanoutOneBinding() {
        return BindingBuilder.bind(fanoutOneQueue()).to(fanoutExchange());
    }

    @Bean
    Binding fanoutTwoBinding() {
        return BindingBuilder.bind(fanoutTwoQueue()).to(fanoutExchange());
    }
}
