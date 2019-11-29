package com.zhengjian.sample.springboot.rabbitmq.receiver;

import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQFanoutConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiver {
    // 监听队列
    @RabbitListener(queues = RabbitMQFanoutConfig.QUEUE_FANOUT_ONE)
    public void handler1(String msg) {
        System.out.println("FanoutReceiver:handler1:" + msg);
    }

    // 监听队列
    @RabbitListener(queues = RabbitMQFanoutConfig.QUEUE_FANOUT_TWO)
    public void handler2(String msg) {
        System.out.println("FanoutReceiver:handler2:" + msg);
    }
}
