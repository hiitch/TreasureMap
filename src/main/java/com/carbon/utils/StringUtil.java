package com.carbon.utils;

public class StringUtil {

    public static String[] sanitize(String s) {
        String sWithoutWhiteSpace = s.replaceAll(" ", "");
        return sWithoutWhiteSpace.split("-");
    }
}
