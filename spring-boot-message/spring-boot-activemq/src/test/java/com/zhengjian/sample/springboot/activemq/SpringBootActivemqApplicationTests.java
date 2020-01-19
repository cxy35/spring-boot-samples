package com.zhengjian.sample.springboot.activemq;

import com.zhengjian.sample.springboot.activemq.pojo.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;

import java.util.Date;

@SpringBootTest
class SpringBootActivemqApplicationTests {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    void contextLoads() {
        Message message = new Message();
        message.setContent("Hello ActiveMQ...");
        message.setSendDate(new Date());
        jmsMessagingTemplate.convertAndSend("myQueue", message);
    }

}
