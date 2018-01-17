package com.zrich;


import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class CharsetTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String code="ACED0005001D";
        String fullKey = "\\xAC\\xED\\x00\\x05t\\x00\\x1DSIGNUP.VERIFYCODE.13760786079";
        System.out.println(Long.parseLong(code, 16));
        byte[] bytes = new String("OMA").getBytes("UTF-8");
        System.out.println(Arrays.toString(bytes));
        System.out.println(new String(fullKey.getBytes(), "UTF-8"));
    }

}
