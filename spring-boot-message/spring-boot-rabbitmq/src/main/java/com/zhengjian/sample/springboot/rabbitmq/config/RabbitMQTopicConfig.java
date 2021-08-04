package com.zhengjian.sample.springboot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopicConfig {
    public static final String EXCHANGE_NAME_TOPIC = "exchange_topic";
    public static final String QUEUE_NAME_TOPIC_XIAOMI = "queue_topic_xiaomi";
    public static final String QUEUE_NAME_TOPIC_HUAWEI = "queue_topic_huawei";
    public static final String QUEUE_NAME_TOPIC_PHONE = "queue_topic_phone";

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME_TOPIC, true, false);
    }

    @Bean
    Queue xiaomiQueue() {
        return new Queue(RabbitMQTopicConfig.QUEUE_NAME_TOPIC_XIAOMI, true);
    }

    @Bean
    Queue huaweiQueue() {
        return new Queue(RabbitMQTopicConfig.QUEUE_NAME_TOPIC_HUAWEI, true);
    }

    @Bean
    Queue phoneQueue() {
        return new Queue(RabbitMQTopicConfig.QUEUE_NAME_TOPIC_PHONE, true);
    }

    @Bean
    Binding xiaomiBinding() {
        // routingKey以xiaomi打头
        return BindingBuilder.bind(xiaomiQueue()).to(topicExchange()).with("xiaomi.#");
    }

    @Bean
    Binding huaweiBinding() {
        // routingKey以huawei打头
        return BindingBuilder.bind(huaweiQueue()).to(topicExchange()).with("huawei.#");
    }

    @Bean
    Binding phoneBinding() {
        // routingKey包含phone
        return BindingBuilder.bind(phoneQueue()).to(topicExchange()).with("#.phone.#");
    }
}
