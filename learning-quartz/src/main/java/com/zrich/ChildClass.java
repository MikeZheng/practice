package com.zrich;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/8/15.
 */
public class ChildClass extends SuperClass {
    private String childClazz="1456";

    public static void main(String[] args) {
        ChildClass childClass = new ChildClass();

        System.out.println(Arrays.toString(childClass.getClass().getFields()));
        System.out.println(Arrays.toString(childClass.getClass().getDeclaredFields()));
    }
}
