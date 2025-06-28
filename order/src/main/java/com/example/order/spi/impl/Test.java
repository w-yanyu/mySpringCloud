package com.example.order.spi.impl;

import com.example.order.spi.TestSpi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @ClassName Test
 * @Author yanyu
 * @Date 2024/7/3 22:23
 * @Version 1.0
 * @Description TODO
 **/
public class Test {
    public static void main(String[] args) {
        ServiceLoader<TestSpi> load = ServiceLoader.load(TestSpi.class);
        for (TestSpi next : load) {
            next.print();
        }

        System.out.println(4|2);
        System.out.println(14|12);

        int count;
        int count1;
        int a =10;
        int b =12;
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 10000; i++) {
            count=i;
            a ^= b;
            b ^= a;
            a ^= b;
        }

        System.out.println(System.currentTimeMillis());
        int c;

        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 10000; i++) {
            count1=i;
            c = a;
            a = b;
            b = c;
        }

        System.out.println(System.currentTimeMillis());
        System.out.println(a+":"+b);
    }
}
