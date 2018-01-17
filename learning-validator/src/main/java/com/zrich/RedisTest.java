package com.zrich;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RedisTest {

    public static void main(String[] args) {
        byte[] bytes  = {84, -19, 0, 5, 116, 1, 1, 101, 103, 115, 99, 95, 115, 99, 112, 95, 107, 101, 121, 95, 115, 115, 111, 95, 117, 115, 101, 114, 95, 101, 121, 74, 104, 98, 71, 99, 105, 79, 105, 74, 83, 85, 122, 85, 120, 77, 105, 74, 57, 46, 101, 121, 74, 122, 100, 87, 73, 105, 79, 105, 74, 48, 90, 88, 78, 48, 73, 105, 119, 105, 90, 88, 104, 119, 73, 106, 111, 120, 78, 84, 69, 50, 77, 84, 89, 121, 77, 106, 99, 53, 102, 81, 46, 73, 69, 52, 55, 79, 95, 111, 90, 53, 72, 113, 68, 120, 82, 84, 113, 68, 71, 81, 70, 83, 107, 105, 81, 120, 106, 111, 117, 113, 106, 95, 45, 121, 86, 88, 104, 68, 73, 54, 65, 115, 110, 122, 56, 120, 85, 73, 99, 84, 111, 97, 119, 122, 95, 81, 81, 48, 77, 77, 73, 68, 117, 80, 54, 121, 89, 90, 49, 88, 52, 56, 121, 109, 102, 97, 54, 97, 56, 104, 111, 70, 77, 77, 75, 122, 71, 95, 56, 108, 119, 79, 83, 120, 97, 67, 82, 103, 83, 83, 110, 102, 84, 56, 88, 76, 56, 69, 116, 111, 120, 89, 104, 119, 115, 87, 113, 115, 88, 99, 98, 87, 121, 95, 75, 118, 86, 112, 80, 121, 48, 85, 89, 90, 81, 75, 101, 111, 53, 87, 57, 55, 80, 49, 108, 109, 122, 78, 102, 67, 110, 111, 70, 68, 68, 72, 108, 73, 121, 68, 83, 120, 122, 74, 75, 119, 102, 120, 97, 65, 108, 85};

        System.out.println(new String(bytes));

        String key = "egsc_scp_key_sso_user_eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNTE2MTYyMjc5fQ.IE47O_oZ5HqDxRTqDGQFSkiQxjouqj_-yVXhDI6Asnz8xUIcToawz_QQ0MMIDuP6yYZ1X48ymfa6a8hoFMMKzG_8lwOSxaCRgSSnfT8XL8EtoxYhwsWqsXcbWy_KvVpPy0UYZQKeo5W97P1lmzNfCnoFDDHlIyDSxzJKwfxaAlU";
        byte[] keyBytes = {101, 103, 115, 99, 95, 115, 99, 112, 95, 107, 101, 121, 95, 115, 115, 111, 95, 117, 115, 101, 114, 95, 101, 121, 74, 104, 98, 71, 99, 105, 79, 105, 74, 83, 85, 122, 85, 120, 77, 105, 74, 57, 46, 101, 121, 74, 122, 100, 87, 73, 105, 79, 105, 74, 48, 90, 88, 78, 48, 73, 105, 119, 105, 90, 88, 104, 119, 73, 106, 111, 120, 78, 84, 69, 50, 77, 84, 89, 121, 77, 106, 99, 53, 102, 81, 46, 73, 69, 52, 55, 79, 95, 111, 90, 53, 72, 113, 68, 120, 82, 84, 113, 68, 71, 81, 70, 83, 107, 105, 81, 120, 106, 111, 117, 113, 106, 95, 45, 121, 86, 88, 104, 68, 73, 54, 65, 115, 110, 122, 56, 120, 85, 73, 99, 84, 111, 97, 119, 122, 95, 81, 81, 48, 77, 77, 73, 68, 117, 80, 54, 121, 89, 90, 49, 88, 52, 56, 121, 109, 102, 97, 54, 97, 56, 104, 111, 70, 77, 77, 75, 122, 71, 95, 56, 108, 119, 79, 83, 120, 97, 67, 82, 103, 83, 83, 110, 102, 84, 56, 88, 76, 56, 69, 116, 111, 120, 89, 104, 119, 115, 87, 113, 115, 88, 99, 98, 87, 121, 95, 75, 118, 86, 112, 80, 121, 48, 85, 89, 90, 81, 75, 101, 111, 53, 87, 57, 55, 80, 49, 108, 109, 122, 78, 102, 67, 110, 111, 70, 68, 68, 72, 108, 73, 121, 68, 83, 120, 122, 74, 75, 119, 102, 120, 97, 65, 108, 85};
        System.out.println(Arrays.toString(key.getBytes()));

        System.out.println(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            System.out.printf("%4d, ", bytes[bytes.length-i-1]);
        }

        System.out.println();

        for (int i = 0; i < keyBytes.length; i++) {
            System.out.printf("%4d, ", keyBytes[keyBytes.length-i-1]);
        }
    }

}