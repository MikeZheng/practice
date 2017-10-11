package com.zrich;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        final String channelCode = "E02256";
        final String key = "YF306CDV";
        final String thriftIP = "localhost";
        final int port = 8099;

        final PlaceTeamOrderHandler placeTeamOrderHandler = new PlaceTeamOrderHandler();
        final OrderPayNotifyHandler orderPayNotifyHandler = new OrderPayNotifyHandler();
        final PlaceOrderHandler placeOrderHandler = new PlaceOrderHandler();

        System.out.println("Batch place order start at " + new Date());



         boolean stop = false;

//        while (!stop) {
            /*String orderNo = placeOrderHandler.execute(channelCode, key, thriftIP, port);
            if (orderNo != null && orderNo.trim().length() > 0) {
                orderPayNotifyHandler.execute(channelCode, key, thriftIP, port, orderNo);
            }*/
//        }




        ExecutorService executor = Executors.newFixedThreadPool(1);
        for (int i = 1; i < 50; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    String orderNo = placeTeamOrderHandler.execute(channelCode, key, thriftIP, port);
                    if (orderNo != null && orderNo.trim().length() > 0) {
                        orderPayNotifyHandler.execute(channelCode, key, thriftIP, port, orderNo);
                    }
                }
            });
        }
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        System.out.println("Batch place order end at " + new Date());
    }
}
