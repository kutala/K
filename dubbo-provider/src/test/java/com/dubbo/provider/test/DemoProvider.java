package com.dubbo.provider.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoProvider {

    public static void main(String[] args) throws Exception {
    	System.out.println("app start");
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"config/applicationContext.xml"});
        context.start();

        System.out.println(" app run ");

        System.in.read(); // 按任意键退出
    }
}
           