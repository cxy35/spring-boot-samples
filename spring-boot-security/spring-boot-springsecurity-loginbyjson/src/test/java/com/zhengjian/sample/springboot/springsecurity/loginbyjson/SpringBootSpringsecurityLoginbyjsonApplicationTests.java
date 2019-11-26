package com.zhengjian.sample.springboot.springsecurity.loginbyjson;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringBootSpringsecurityLoginbyjsonApplicationTests {

    @Test
    void contextLoads() {
        // 同样的明文加密后不重复
        for (int i = 0; i < 10; i++) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            System.out.println(encoder.encode("123456"));
        }
    }

}
