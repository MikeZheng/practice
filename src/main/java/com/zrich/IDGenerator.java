package com.zrich;



import java.util.*;

/**
 * Created by zrich on 16-11-13:21:52.
 *
 */

public class IDGenerator {



    private final Calendar calendar = Calendar.getInstance();
    private final Map<Integer, String> validMap = new HashMap<Integer, String>(11);
    private final int CURRENT_YEAR = calendar.get(Calendar.YEAR);
    private final Random random = new Random(System.currentTimeMillis());

    public IDGenerator() {
        validMap.put(0, "1");
        validMap.put(1, "0");
        validMap.put(2, "X");
        validMap.put(3, "9");
        validMap.put(4, "8");
        validMap.put(5, "7");
        validMap.put(6, "6");
        validMap.put(7, "5");
        validMap.put(8, "4");
        validMap.put(9, "3");
        validMap.put(10, "2");
    }

    public String generate() {
        // TODO very important performance
        StringBuilder id = new StringBuilder();

        int year = random.nextInt(80);
        calendar.set(Calendar.YEAR, CURRENT_YEAR - 80 + year);
        int month = random.nextInt(12);
        calendar.set(Calendar.MONTH, month);
        int day = random.nextInt(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        int sequence = random.nextInt(1000);

        id.append(350301);
        id.append(calendar.get(Calendar.YEAR));
        id.append(month + 1 < 10 ? "0" + (month + 1) : month + 1);
        id.append(day + 1 < 10 ? "0" + (day + 1) : day + 1);
        id.append(sequence < 100 ? (sequence < 10 ? "00" + sequence : "0" + sequence) : sequence);
        String check = check(id.toString());
        id.append(check);
        return id.toString();

    }

    private String check(String s) {
        if (s == null || s.length() != 17) {
            return "";
        }
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = Integer.valueOf(String.valueOf(s.charAt(i)));
            int w = (int) Math.pow(2, s.length() - i);
            w = w % 11;
            sum += w * num;
        }
        int y = sum % 11;
        String check = validMap.get(y);
        if (check == null) {
            throw new IllegalArgumentException("Can't find check of " + y);
        }
        return check;
    }

    private String check2(String s) {
        String[] wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };

        if (s == null || s.length() != 17) {
            return "";
        }
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = Integer.valueOf(String.valueOf(s.charAt(i)));
            sum += num * Integer.valueOf(wi[i]);
        }
        int y = sum % 11;
        String check = validMap.get(y);
        if (check == null) {
            throw new IllegalArgumentException("Can't find check of " + y);
        }
        return check;
    }

    public static void main(String[] args) {
        IDGenerator generator = new IDGenerator();
        for (int i = 0; i < 100; i++) {
            System.out.println(generator.generate());
        }

//        System.out.println(generator.check("3503012005031622"));
//        System.out.println(generator.check2("3503011984012848"));


    }


}
