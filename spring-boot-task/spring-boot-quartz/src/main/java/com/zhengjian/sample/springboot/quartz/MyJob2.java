package com.zhengjian.sample.springboot.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Author zhengjian
 * @Date 2019-06-04 15:15
 */
public class MyJob2 extends QuartzJobBean {
    HelloService helloService;

    public HelloService getHelloService() {
        return helloService;
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        helloService.sayHello();
    }
}
