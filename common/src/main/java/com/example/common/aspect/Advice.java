package com.example.common.aspect;

import jdk.nashorn.internal.objects.annotations.Function;

/**
 * @Author yanyu
 * @Date 2024/4/28 15:36
 * @Version 1.0
 * @Description TODO
 **/

@FunctionalInterface
public interface Advice {
    Object handle();
}
