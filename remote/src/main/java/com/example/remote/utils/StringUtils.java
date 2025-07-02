package com.example.remote.utils;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || "".equals(str.trim());
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }
}
