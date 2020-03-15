package com.cxy35.sample.springboot.quartz.config;

import com.cxy35.sample.springboot.quartz.job.MyJob2;
import org.quartz.JobDataMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

import java.util.Date;

/**
 * 在 Quartz 配置类中，主要配置两个东西：1.JobDetail（要做的事情） 2.Trigger（什么时候做）
 * <p>
 * JobDetail 有两种不同的配置方式：
 * 1. MethodInvokingJobDetailFactoryBean
 * 2. JobDetailFactoryBean
 */
@Configuration
public class QuartzConfig {
    // JobDetail1
    // JobDetail 配置方式1：这里使用 MyJob1 测试，指定 bean 和方法，无法传参
    @Bean
    MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("myJob1");
        bean.setTargetMethod("sayHello");
        return bean;
    }

    // JobDetail2
    // JobDetail 配置方式2：这里使用 MyJob2 测试，支持传参
    @Bean
    JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        JobDataMap map = new JobDataMap();
        map.put("name", "cxy35");
        bean.setJobDataMap(map);
        bean.setJobClass(MyJob2.class);
        return bean;
    }

    // Trigger1：这里使用 JobDetail1 测试
    @Bean
    SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setStartTime(new Date());
        bean.setRepeatCount(5);
        bean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        bean.setRepeatInterval(3000);
        return bean;
    }

    // Trigger2：这里使用 JobDetail2 测试
    @Bean
    CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setCronExpression("0/10 * * * * ?");
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        return bean;
    }

    // 注册 Trigger1 和 Trigger2
    @Bean
    SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(simpleTriggerFactoryBean().getObject(), cronTriggerFactoryBean().getObject());
        return bean;
    }
}