package com.example.common.annotation;

import java.lang.annotation.*;

/**
 * 打印请求日志
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebLog {
}
