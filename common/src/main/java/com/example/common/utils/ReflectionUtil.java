package com.example.common.utils;

/**
 * @ClassName ReflectionUtil
 * @Author yanyu
 * @Date 2025/2/5 17:41
 * @Version 1.0
 * @Description TODO
 **/
public class ReflectionUtil {
    public static Class<?> classForName(String className) {
        try {
            return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException var2) {
            throw new IllegalArgumentException(var2);
        }
    }
}
