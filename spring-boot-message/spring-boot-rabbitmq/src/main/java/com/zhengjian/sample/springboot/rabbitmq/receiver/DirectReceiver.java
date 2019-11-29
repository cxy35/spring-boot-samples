package com.zhengjian.sample.springboot.rabbitmq.receiver;

import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQDirectConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {
    // 监听队列
    @RabbitListener(queues = RabbitMQDirectConfig.QUEUE_DIRECT)
    public void handler1(String msg) {
        System.out.println("DirectReceiver:handler1>>>" + msg);
    }
}
