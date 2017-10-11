package com.zrich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>Description: </p>
 * @author 郑振富
 * @version 1.0
 *          <p>Company:Mopon</p>
 *          <p>Copyright:Copyright(c)2017</p>
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class ConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}
