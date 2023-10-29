package org.example;

import java.util.Arrays;

public class StringSlice {
    public static String getStringSlice(String str, int x) {
        String[] symbol = str.split("");
        System.out.printf(Arrays.toString(symbol));
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < x; i++) {
            newStr.append(symbol[i]);
        }

        return newStr.toString();
    }

}