package org.remote.spring.config;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class RemoteChannelProxyFactoryBean implements FactoryBean<Object> {

    public static final String PROPERTY_NAME = "interfaces";

    private InvocationHandler invocationHandler;

    private Class<?> interfaces;

    {
        //invocationHandler =
        //DefaultRemoteEnhancerManager defaultRemoteEnhancerManager = DefaultRemoteEnhancerManager.getInstance();
        //defaultRemoteEnhancerManager.registerEnhancer();
    }

    public RemoteChannelProxyFactoryBean(Class<?> interfaces) {
        this.interfaces = interfaces;
    }

    @Override
    public Object getObject() throws Exception {
        Object o = Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{interfaces},
                (proxy, method, args) -> {
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
