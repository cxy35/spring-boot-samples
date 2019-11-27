package com.zhengjian.sample.springboot.commandlinerunner.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author zhengjian
 * @Date 2019-07-24 17:32
 */
@Component
// 数字越小，优先级越大，默认情况下，优先级的值为 Integer.MAX_VALUE，表示优先级最低
@Order(100)
public class MyCommandLineRunner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyCommandLineRunner2>>>"+Arrays.toString(args));
    }
}
