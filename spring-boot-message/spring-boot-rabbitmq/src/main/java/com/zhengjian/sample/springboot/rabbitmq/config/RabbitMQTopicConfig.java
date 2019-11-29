package com.zhengjian.sample.springboot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopicConfig {
    public static final String NAME_TOPIC = "name_topic";
    public static final String QUEUE_TOPIC_XIAOMI = "queue_topic_xiaomi";
    public static final String QUEUE_TOPIC_HUAWEI = "queue_topic_huawei";
    public static final String QUEUE_TOPIC_PHONE = "queue_topic_phone";

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(NAME_TOPIC, true, false);
    }

    @Bean
    Queue xiaomi() {
        return new Queue(RabbitMQTopicConfig.QUEUE_TOPIC_XIAOMI);
    }

    @Bean
    Queue huawei() {
        return new Queue(RabbitMQTopicConfig.QUEUE_TOPIC_HUAWEI);
    }

    @Bean
    Queue phone() {
        return new Queue(RabbitMQTopicConfig.QUEUE_TOPIC_PHONE);
    }

    @Bean
    Binding xiaomiBinding() {
        // routingKey以xiaomi打头
        return BindingBuilder.bind(xiaomi()).to(topicExchange()).with("xiaomi.#");
    }

    @Bean
    Binding huaweiBinding() {
        // routingKey以huawei打头
        return BindingBuilder.bind(huawei()).to(topicExchange()).with("huawei.#");
    }

    @Bean
    Binding phoneBinding() {
        // routingKey包含phone
        return BindingBuilder.bind(phone()).to(topicExchange()).with("#.phone.#");
    }
}
