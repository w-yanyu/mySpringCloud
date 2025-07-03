package com.example.spring.remote.config;

import com.example.remote.config.RemoteChannelProxyFactory;
import com.example.remote.manager.impl.DefaultRemoteEnhancerManager;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class RemoteChannelProxyFactoryBean implements FactoryBean<Object> {

    public static final String PROPERTY_NAME = "interfaces";

    private InvocationHandler invocationHandler;

    private Class<?> interfaces;

    {
        //invocationHandler =
        DefaultRemoteEnhancerManager defaultRemoteEnhancerManager = DefaultRemoteEnhancerManager.getInstance();
        //defaultRemoteEnhancerManager.registerEnhancer();
    }

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
