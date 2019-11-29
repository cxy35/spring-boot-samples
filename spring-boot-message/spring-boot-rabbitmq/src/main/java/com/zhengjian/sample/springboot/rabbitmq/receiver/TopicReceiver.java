package com.zhengjian.sample.springboot.rabbitmq.receiver;

import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQTopicConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {
    // 监听队列
    @RabbitListener(queues = RabbitMQTopicConfig.QUEUE_TOPIC_XIAOMI)
    public void handler1(String msg) {
        System.out.println("TopicReceiver:handler1:" + msg);
    }

    // 监听队列
    @RabbitListener(queues = RabbitMQTopicConfig.QUEUE_TOPIC_HUAWEI)
    public void handler2(String msg) {
        System.out.println("TopicReceiver:handler2:" + msg);
    }

    // 监听队列
    @RabbitListener(queues = RabbitMQTopicConfig.QUEUE_TOPIC_PHONE)
    public void handler3(String msg) {
        System.out.println("TopicReceiver:handler3:" + msg);
    }
}
