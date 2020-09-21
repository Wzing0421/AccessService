package com.wang;

import com.wang.server.httpServer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) throws Exception {

        //1.创建spring的ioc容器对象

        ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");

        httpServer server = (httpServer) ctx.getBean("httpServer");
        server.run();

    }
}
