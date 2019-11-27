package com.zhengjian.sample.springboot.websocket.groupchat.controller;

import com.zhengjian.sample.springboot.websocket.groupchat.pojo.GroupChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    // 方法1
//    @MessageMapping("/hello") // 通过/app/hello访问
//    @SendTo("/topic/chat")
//    public GroupChatMessage hello(GroupChatMessage message) {
//        return message;
//    }

    // 方法2
    @MessageMapping("/hello") // 通过/app/hello访问进来
    public void hello(GroupChatMessage message) {
        // 将收到的消息发送到指定目的地，订阅的人都能收到，需要当前连接上，后面连接上的收不到之前的消息
        simpMessagingTemplate.convertAndSend("/topic/chat", message);
    }
}
