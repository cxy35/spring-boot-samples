package com.cxy35.sample.springboot.mystarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cxy35") // 类型安全的属性注入，指定配置的前缀
public class HelloProperties {
    private static final String DEFAULT_NAME = "默认名称";
    private static final String DEFAULT_MSG = "默认消息";
    private String name = DEFAULT_NAME;
    private String msg = DEFAULT_MSG;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
