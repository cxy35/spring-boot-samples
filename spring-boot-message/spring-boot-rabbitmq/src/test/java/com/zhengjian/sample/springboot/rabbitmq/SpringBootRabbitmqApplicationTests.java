package com.zhengjian.sample.springboot.rabbitmq;

import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQDirectConfig;
import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQFanoutConfig;
import com.zhengjian.sample.springboot.rabbitmq.config.RabbitMQHeadersConfig;
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
    public void fanout() {
        // 发布订阅模式：会发送到与对应 fanout 类型交换机绑定的所有队列
        rabbitTemplate.convertAndSend(RabbitMQFanoutConfig.EXCHANGE_NAME_FANOUT, null, "Hello RabbitMQFanout...");
    }

    @Test
    public void direct() {
        // 路由模式：会发送到与对应 direct 类型交换机绑定且与 routingKey 对应的所有队列
        rabbitTemplate.convertAndSend(RabbitMQDirectConfig.EXCHANGE_NAME_DIRECT, "one", "Hello RabbitMQDirect...");
    }

    @Test
    public void topic() {
        // 主题模式：会发送到与对应 topic 类型交换机绑定且与 routingKey 匹配的所有队列
        // 匹配 xiaomi
        rabbitTemplate.convertAndSend(RabbitMQTopicConfig.EXCHANGE_NAME_TOPIC, "xiaomi.news", "小米新闻");
        // 匹配 phone
        rabbitTemplate.convertAndSend(RabbitMQTopicConfig.EXCHANGE_NAME_TOPIC, "vivo.phone", "vivo手机");
        // 匹配 huawei 和 phone
        rabbitTemplate.convertAndSend(RabbitMQTopicConfig.EXCHANGE_NAME_TOPIC, "huawei.phone", "华为手机");
    }

    @Test
    public void headers() {
        // Headers 模式：会发送到与对应 headers 类型交换机绑定且 message 匹配的所有队列
        Message nameMsg = MessageBuilder.withBody("Hello Name...".getBytes()).setHeader("name", "zhangsan").build();
        Message nameMsg2 = MessageBuilder.withBody("Hello Name2...".getBytes()).setHeader("name", "lisi").build();
        Message ageMsg = MessageBuilder.withBody("Hello Age...".getBytes()).setHeader("age", "99").build();
        rabbitTemplate.send(RabbitMQHeadersConfig.EXCHANGE_NAME_HEADERS, null, nameMsg);
        rabbitTemplate.send(RabbitMQHeadersConfig.EXCHANGE_NAME_HEADERS, null, nameMsg2);
        rabbitTemplate.send(RabbitMQHeadersConfig.EXCHANGE_NAME_HEADERS, null, ageMsg);
    }
}
