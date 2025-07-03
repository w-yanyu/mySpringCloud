package com.example.remote.util;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || "".equals(str.trim());
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String determineBeanNameFromClass(String className) {
        if (className != null && className.length() > 0) {
            char firstChar = className.charAt(0);
            if (Character.isUpperCase(firstChar)) {
                return Character.toLowerCase(firstChar) + className.substring(1);
            }
        }
        return className;
    }
}
