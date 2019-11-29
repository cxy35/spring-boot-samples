package com.zhengjian.sample.springboot.rabbitmq;

import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQDirectConfig;
import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQFanoutConfig;
import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQHeaderConfig;
import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQTopicConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootRabbitmqApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void direct() {
        // 会发送到指定的队列
        rabbitTemplate.convertAndSend(RabbitMQDirectConfig.QUEUE_DIRECT, "Hello RabbitMQDirect...");
    }

    @Test
    public void fanout() {
        // 会发送到与FanoutExchange绑定的所有队列
        rabbitTemplate.convertAndSend(RabbitMQFanoutConfig.NAME_FANOUT, null, "Hello RabbitMQFanout...");
    }

    @Test
    public void topic() {
        // 会发送到与TopicExchange绑定且routingKey匹配上的所有队列
        rabbitTemplate.convertAndSend(RabbitMQTopicConfig.NAME_TOPIC, "xiaomi.news", "小米新闻");
        rabbitTemplate.convertAndSend(RabbitMQTopicConfig.NAME_TOPIC, "vivo.phone", "vivo手机");
        rabbitTemplate.convertAndSend(RabbitMQTopicConfig.NAME_TOPIC, "huawei.phone", "华为手机");
    }

    @Test
    public void header() {
        // 会发送到与HeadersExchange绑定且message匹配上的所有队列
        Message nameMsg = MessageBuilder.withBody("Hello Name...".getBytes()).setHeader("name", "zhangsan").build();
        Message nameMsg2 = MessageBuilder.withBody("Hello Name2...".getBytes()).setHeader("name", "lisi").build();
        Message ageMsg = MessageBuilder.withBody("Hello Age...".getBytes()).setHeader("age", "99").build();
        rabbitTemplate.send(RabbitMQHeaderConfig.NAME_HEADER, null, nameMsg);
        rabbitTemplate.send(RabbitMQHeaderConfig.NAME_HEADER, null, nameMsg2);
        rabbitTemplate.send(RabbitMQHeaderConfig.NAME_HEADER, null, ageMsg);
    }
}
