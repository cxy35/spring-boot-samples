package com.zhengjian.sample.springboot.activemq.receiver;

import com.zhengjian.sample.springboot.activemq.config.ActiveMQConfig;
import com.zhengjian.sample.springboot.activemq.pojo.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQReceiver {
    // 监听队列
    @JmsListener(destination = ActiveMQConfig.QUEUE_NAME)
    public void receive(Message message) {
        System.out.println(message);
    }
}
