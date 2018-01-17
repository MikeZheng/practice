package com.zrich;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class EncryptUtil {
    private static final String SITE_WIDE_SECRET = "35637289C2354E38B5FC743983DFBDD6";
    private static final PasswordEncoder ENCODER = new StandardPasswordEncoder("35637289C2354E38B5FC743983DFBDD6");

    public EncryptUtil() {
    }

    public static String encrypt(String rawPassword) {
        return ENCODER.encode(rawPassword);
    }

    public static boolean match(String rawPassword, String password) {
        return ENCODER.matches(rawPassword, password);
    }

    public static void main(String[] args) {
        System.out.println(EncryptUtil.encrypt("alkdfjljfoqwieruqpoeruqpweruqp"));
    }
}
