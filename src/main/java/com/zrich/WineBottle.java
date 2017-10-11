package com.zrich;

/**
 * Created by Administrator on 2017/7/19.
 */
public class WineBottle {


    public static void main(String[] args) {

        int money = 10;
        int price = 2;
        int bottle = 2;
        int cap = 4;
        int[] history = new int[]{0,0,0,0};


        int current = money / price;

        history[2]=current;
        history[3]=current;
        do {
            history[0]=history[2]/cap;
            history[1]=history[2]/bottle;

        } while (true);

    }
}
