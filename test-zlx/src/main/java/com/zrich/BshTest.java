package com.zrich;

import bsh.EvalError;
import bsh.Interpreter;
import com.sun.org.apache.regexp.internal.RE;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BshTest {

    public static final String REGEX = "(([0-9]+)[月|个月|期]+)";

    public static void main(String[] args) throws EvalError {
        Interpreter interpreter = new Interpreter();
        String expr = "\"3个月\".contains(\"月\");";
        System.out.println(interpreter.eval(expr));

        Pattern pattern = Pattern.compile(REGEX);
        match(REGEX, "我3个月要112月的sdf");
        match(REGEX, "我打算分成24个月qwer");
        match(REGEX, "我打12算分成24期wer");
    }

    public static void match(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            System.out.println(matcher.groupCount());
                System.out.println(matcher.group(1));
                System.out.println(matcher.group(2));


        }
    }
}
