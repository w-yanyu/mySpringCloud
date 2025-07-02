package com.spring.remote.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

public class RemoteChannelProxyFactoryBean implements FactoryBean<Object> {

    public static final String PROPERTY_NAME = "interfaces";

    private Class<?> interfaces;

    public RemoteChannelProxyFactoryBean(Class<?> interfaces) {
        this.interfaces = interfaces;
    }

    @Override
    public Object getObject() throws Exception {
        System.out.println("hehehheh"+interfaces);
        Object o = Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{interfaces},
                (proxy, method, args) -> {
                    System.out.println("Executing class name : " + getObjectType());
                    System.out.println("Executing method: " + method.getName());
                    return null;  // 适当返回值
                }
        );
        return o;
    }

    @Override
    public Class<?> getObjectType() {
        return interfaces;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
