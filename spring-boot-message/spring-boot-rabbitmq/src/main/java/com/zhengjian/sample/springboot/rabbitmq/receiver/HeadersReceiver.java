package com.zhengjian.sample.springboot.rabbitmq.receiver;

import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQHeadersConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeadersReceiver {
    // 监听队列
    @RabbitListener(queues = RabbitMQHeadersConfig.QUEUE_NAME_HEADERS_NAME)
    public void handler1(byte[] msg) {
        System.out.println("HeaderReceiver:handler1:" + new String(msg, 0, msg.length));
    }

    // 监听队列
    @RabbitListener(queues = RabbitMQHeadersConfig.QUEUE_NAME_HEADERS_AGE)
    public void handler2(byte[] msg) {
        System.out.println("HeaderReceiver:handler2:" + new String(msg, 0, msg.length));
    }
}
