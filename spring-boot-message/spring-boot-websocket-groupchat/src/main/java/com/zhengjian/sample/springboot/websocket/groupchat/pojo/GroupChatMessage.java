package com.zhengjian.sample.springboot.websocket.groupchat.pojo;

// 在线群聊消息对象
public class GroupChatMessage {
    private String name;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
