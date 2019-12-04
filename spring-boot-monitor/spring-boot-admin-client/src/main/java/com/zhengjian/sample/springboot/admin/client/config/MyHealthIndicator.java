package com.zhengjian.sample.springboot.admin.client.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

// 自定义Health信息，优先级比配置文件高
@Component
public class MyHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
//        return Health.status("FATAL").withDetail("msg", "网络断开...").build();
        return Health.up().withDetail("msg", "网络连接正常...").build();
    }
}