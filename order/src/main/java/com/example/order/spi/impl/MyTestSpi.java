package com.example.order.spi.impl;

import com.example.order.spi.TestSpi;

/**
 * @ClassName MyTestSpi
 * @Author yanyu
 * @Date 2024/7/3 22:22
 * @Version 1.0
 * @Description TODO
 **/
public class MyTestSpi implements TestSpi {
    @Override
    public void print() {
        System.out.println("MyTestSpi...");
    }
}
