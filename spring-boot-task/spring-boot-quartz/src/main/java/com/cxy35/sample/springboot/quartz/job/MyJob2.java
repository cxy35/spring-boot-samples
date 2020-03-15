package com.cxy35.sample.springboot.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

// Job 定义方式2：继承 QuartzJobBean 并实现默认的方法（支持传参）
public class MyJob2 extends QuartzJobBean {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("MyJob2 >>> " + name + ":" + new Date());
    }
}
