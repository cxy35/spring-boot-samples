package com.zhengjian.sample.springboot.rabbitmq.receiver;

import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQFanoutConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiver {
    // 监听队列
    @RabbitListener(queues = RabbitMQFanoutConfig.QUEUE_NAME_FANOUT_ONE)
    public void handlerOne(String msg) {
        System.out.println("FanoutReceiver:handlerOne:" + msg);
    }

    // 监听队列
    @RabbitListener(queues = RabbitMQFanoutConfig.QUEUE_NAME_FANOUT_TWO)
    public void handlerTwo(String msg) {
        System.out.println("FanoutReceiver:handlerTwo:" + msg);
    }
}
