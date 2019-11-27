package com.zhengjian.sample.springboot.websocket.privatechat.controller;

import com.zhengjian.sample.springboot.websocket.privatechat.pojo.PrivateChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class HelloController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello") // 通过/app/hello访问进来
    public void hello(Principal principal, PrivateChatMessage message) {
        // 将收到的消息发送给指定的人，如果他有订阅就能收到，需要当前连接上，后面连接上的收不到之前的消息
        message.setFrom(principal.getName());
        simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/queue/chat", message);
    }
}
