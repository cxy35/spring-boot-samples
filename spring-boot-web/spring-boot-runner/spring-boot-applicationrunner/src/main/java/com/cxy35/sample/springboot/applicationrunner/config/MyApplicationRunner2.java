package com.cxy35.sample.springboot.applicationrunner.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
// 数字越小，优先级越大，默认情况下，优先级的值为 Integer.MAX_VALUE，表示优先级最低
@Order(100)
public class MyApplicationRunner2 implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 获取命令行中的所有参数
        String[] sourceArgs = args.getSourceArgs();
        System.out.println("sourceArgs:" + Arrays.toString(sourceArgs));

        // 获取命令行中的无 key 参数（和 CommandLineRunner 一样）
        List<String> nonOptionArgs = args.getNonOptionArgs();
        System.out.println("nonOptionArgs:" + nonOptionArgs);

        // 获取所有 key/value 形式的参数的 key
        Set<String> optionNames = args.getOptionNames();
        System.out.println("optionNames/optionValues>>>:");
        for (String optionName : optionNames) {
            // 根据 key 获取 key/value 形式的参数的 value
            System.out.println(optionName + ":" + args.getOptionValues(optionName));
        }

        System.out.println(">>>>>>>>>>>>>>> MyApplicationRunner2 结束>>>>>>>>>>>>>>>>");
    }
}
