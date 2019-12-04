package com.zhengjian.sample.springboot.admin.client.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// 自定义Info信息，优先级比配置文件高
@Component
public class MyInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> info = new HashMap<>();
        info.put("name", "张三2");
        info.put("email", "zhangsan2@gmail.com");
        builder.withDetail("author", info);
    }
}
