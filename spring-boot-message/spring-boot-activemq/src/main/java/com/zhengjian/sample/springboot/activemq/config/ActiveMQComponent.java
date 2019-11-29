package com.zhengjian.sample.springboot.activemq.config;

import com.zhengjian.sample.springboot.activemq.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class ActiveMQComponent {
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    Queue queue;

    public void send(Message message) {
        jmsMessagingTemplate.convertAndSend(this.queue, message);
    }

    // 监听队列
    @JmsListener(destination = "myQueue")
    public void receive(Message msg) {
        System.out.println(msg);
    }
}
