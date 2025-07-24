package org.remote.spring.handler;

import org.remote.invocationHandler.RemoteChannelHandler;
import org.remote.service.Remote;
import org.remote.spring.annotation.RemoteHandler;
import org.springframework.beans.factory.FactoryBean;

public class RemoteHandlerFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mapperInterface;

    public RemoteHandlerFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public static boolean registerCondition(Class<?> clazz) {
        return clazz.isAnnotationPresent(RemoteHandler.class) && RemoteChannelHandler.class.isAssignableFrom(clazz);
    }

    @Override
    public T getObject() throws Exception {
        return Remote.getInstance(mapperInterface);
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
