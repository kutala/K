package com.dubbo.provider.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dubbo.api.service.DemoService;

public class DemoConsumer {

    public static void main(String[] args) throws Exception {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "config/applicationContext.xml" });
        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService"); // 获取bean
        // service
        // invocation
        // proxy
        String message = "";
        try {
            message = demoService.sayHi(" 2016-10-20");
            System.out.println(" the message from server is:" + message);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
           