package com.zrich;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/15.
 */
public class MinuteTask {




    public void execute() {
//        System.out.println("MinuteTask start at "+ new Date());
        try {
//            TimeUnit.MINUTES.sleep(2l);
            TimeUnit.SECONDS.sleep(3);
//            System.out.println("MinuteTask running at "+ new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("MinuteTask end at "+ new Date());
    }

    public static void main(String[] args) {
        new MinuteTask().execute();
    }
}
