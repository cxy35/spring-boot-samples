package com.zhengjian.sample.springboot.rabbitmq.receiver;

import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQTopicConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {
    // 监听队列
    @RabbitListener(queues = RabbitMQTopicConfig.QUEUE_NAME_TOPIC_XIAOMI)
    public void handlerXiaomi(String msg) {
        System.out.println("TopicReceiver:handlerXiaomi:" + msg);
    }

    // 监听队列
    @RabbitListener(queues = RabbitMQTopicConfig.QUEUE_NAME_TOPIC_HUAWEI)
    public void handlerHuawei(String msg) {
        System.out.println("TopicReceiver:handlerHuawei:" + msg);
    }

    // 监听队列
    @RabbitListener(queues = RabbitMQTopicConfig.QUEUE_NAME_TOPIC_PHONE)
    public void handlerPhone(String msg) {
        System.out.println("TopicReceiver:handlerPhone:" + msg);
    }
}
