package com.zrich;

import java.lang.reflect.Field;
import java.util.Calendar;

public class Main {

    private static String LESS_THAN = "<";
    private static String GREATER_THAN= ">";
    private static String lineSeparator = java.security.AccessController.doPrivileged(
            new sun.security.action.GetPropertyAction("line.separator"));

    public static void main(String[] args) throws Exception{
	// write your code here
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        calendar.set(Calendar.MONTH, 6);
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

        printVO(new RequestPacketsHead());

        System.out.println(2%5);
    }

    public static void printVO(Object object) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        Class clazz=object.getClass();
        StringBuilder structure = new StringBuilder();
        structure.append(LESS_THAN).append(clazz.getSimpleName()).append(GREATER_THAN)
                .append(lineSeparator);
        int intent = 1;
        for (Field field : fields) {
            field.setAccessible(true);
                structure.append(getIntent(intent)).append(LESS_THAN).append(field.getName()).append(GREATER_THAN)
                        .append(field.get(object)).append(LESS_THAN).append("/").append(field.getName()).append(GREATER_THAN).append(lineSeparator);


        }
        structure.append(LESS_THAN).append("/").append(clazz.getSimpleName()).append(GREATER_THAN);
        System.out.println(structure.toString());
    }

    private static String getIntent(int intent) {
        return " ";
    }
}
