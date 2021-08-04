package com.zhengjian.sample.springboot.rabbitmq.receiver;

import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQDirectConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {
    // 监听队列
    @RabbitListener(queues = RabbitMQDirectConfig.QUEUE_NAME_DIRECT_ONE)
    public void handlerOne(String msg) {
        System.out.println("DirectReceiver:handlerOne:" + msg);
    }

    // 监听队列
    @RabbitListener(queues = RabbitMQDirectConfig.QUEUE_NAME_DIRECT_TWO)
    public void handlerTwo(String msg) {
        System.out.println("DirectReceiver:handlerTwo:" + msg);
    }
}
