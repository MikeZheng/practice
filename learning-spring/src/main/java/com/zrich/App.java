package com.zrich;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-servlet.xml");
        context.start();
        TestController controller = context.getBean(TestController.class);
        controller.count();
        System.out.println("Hello World!");
    }
}
