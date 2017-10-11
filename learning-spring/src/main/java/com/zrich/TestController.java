package com.zrich;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2017/8/15.
 */
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    public void count(){
        testService.count();
    }
}
