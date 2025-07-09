package org.remote.util;

import java.lang.reflect.Method;

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

    public static String determineBeanNameFromClass(Class<?> clazz, String defaultValue) {
        return isEmpty(defaultValue) ? determineBeanNameFromClass(clazz.getSimpleName()) : defaultValue;
    }

    public static String determineMethodName(Method method, String defaultValue) {
        return isEmpty(defaultValue) ? method.getName() : defaultValue;
    }
}
