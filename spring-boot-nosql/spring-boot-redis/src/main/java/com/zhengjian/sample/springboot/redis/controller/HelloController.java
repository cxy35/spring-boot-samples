package com.zhengjian.sample.springboot.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhengjian
 * @Date 2019-07-29 7:53
 */
@RestController
public class HelloController {
    // 模板1：<Object, Object>
    @Autowired
    RedisTemplate redisTemplate;
    // 模板2：<String, String>
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/set")
    public void set() {
        // RedisTemplate 中，key 默认的序列化方案是 JdkSerializationRedisSerializer，key不易读
        // 下面这句可修改为 StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("k1", "v1");
    }

    @GetMapping("/get")
    public void get(){
        ValueOperations ops = redisTemplate.opsForValue();
        System.out.println(ops.get("k1"));
    }

    @GetMapping("/set2")
    public void set2() {
        // StringRedisTemplate 中，key 默认的序列化方案是 StringRedisSerializer
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("k2", "v2");
    }

    @GetMapping("/get2")
    public void get2() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        System.out.println(ops.get("k2"));
    }
}
