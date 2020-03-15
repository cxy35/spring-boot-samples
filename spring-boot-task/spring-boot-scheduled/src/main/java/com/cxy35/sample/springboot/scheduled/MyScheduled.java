package com.cxy35.sample.springboot.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyScheduled {
    // @Scheduled 注解表示开起一个定时任务
    // fixedRate 表示任务执行之间的时间间隔（单位是毫秒），具体是指两次任务的开始时间间隔，即第二次任务开始时，第一次任务可能还没结束。
    @Scheduled(fixedRate = 2000)
    public void fixedRate() {
        System.out.println("fixedRate >>>" + new Date());
    }

    // fixedDelay 表示任务执行之间的时间间隔（单位是毫秒），具体是指本次任务结束到下次任务开始之间的时间间隔。
    @Scheduled(fixedDelay = 2000)
    public void fixedDelay() {
        System.out.println("fixedDelay >>>" + new Date());
    }

    // initialDelay 表示首次任务启动的延迟时间（单位是毫秒）。
    @Scheduled(initialDelay = 2000, fixedDelay = 2000)
    public void initialDelay() {
        System.out.println("initialDelay >>>" + new Date());
    }

    // cron 表达式，每隔 5 秒触发一次
    @Scheduled(cron = "0/5 * * * * ?")
    public void cron() {
        System.out.println("cron >>>" + new Date());
    }
}
