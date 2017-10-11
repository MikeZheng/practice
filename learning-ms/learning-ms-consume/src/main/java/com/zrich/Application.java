package com.zrich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>Description: </p>
 * @author 郑振富
 * @version 1.0
 *          <p>Company:Mopon</p>
 *          <p>Copyright:Copyright(c)2017</p>
 */
@EnableAutoConfiguration
@EnableEurekaClient
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
