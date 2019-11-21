package com.zhengjian.sample.springboot.springcache.redis.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class MyKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        // 自定义缓存的key
        return method.getName() + ":" + Arrays.toString(objects);
    }
}
