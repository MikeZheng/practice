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
//        System.out.println("Hello World!");
//        System.out.println(Arrays.toString(PropertyUtils.getPropertyDescriptors(SuperClass.class)));
    }
}
