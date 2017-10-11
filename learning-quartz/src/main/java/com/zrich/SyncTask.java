package com.zrich;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/24.
 */
public class SyncTask {


    public void execute() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start at " + new Date());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end at " + new Date());
            }
        });

        thread.start();
        while (!thread.getState().equals(Thread.State.TERMINATED)) {

        }

    }

}
