package com.zhengjian.sample.springboot.rabbitmq.receiver;

import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQHeaderConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeaderReceiver {
    // 监听队列
    @RabbitListener(queues = RabbitMQHeaderConfig.QUEUE_NAME_HEADER_NAME)
    public void handler1(byte[] msg) {
        System.out.println("HeaderReceiver:handler1:" + new String(msg, 0, msg.length));
    }

    // 监听队列
    @RabbitListener(queues = RabbitMQHeaderConfig.QUEUE_NAME_HEADER_AGE)
    public void handler2(byte[] msg) {
        System.out.println("HeaderReceiver:handler2:" + new String(msg, 0, msg.length));
    }
}
