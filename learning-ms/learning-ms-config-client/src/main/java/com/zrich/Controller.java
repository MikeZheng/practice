package com.zrich;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@RestController
public class Controller {

    @Value("${lucky-word}")
    private String word;


    @RequestMapping("/show")
    public String showValue() {
        return word == null? "no ": word;
    }
}
