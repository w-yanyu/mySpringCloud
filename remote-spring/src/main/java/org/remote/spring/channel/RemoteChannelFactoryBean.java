package org.remote.spring.channel;

import org.remote.annotation.RemoteChannel;
import org.remote.invocationHandler.RemoteChannelHandler;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RemoteChannelFactoryBean<T> implements FactoryBean<T> {
    private Class<T> mapperInterface;

    public RemoteChannelFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public static boolean registerCondition(Class<?> clazz) {
        return clazz.isAnnotationPresent(RemoteChannel.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getObject() {
        if (!registerCondition(mapperInterface)) {
            throw new IllegalArgumentException("代理接口类型异常！");
        }
        Object o = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{mapperInterface}, new RemoteChannelHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
        return (T) o;
    }

    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }
}
