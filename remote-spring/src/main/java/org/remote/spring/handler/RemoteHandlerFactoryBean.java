package org.remote.spring.handler;

import org.remote.service.Remote;
import org.springframework.beans.factory.FactoryBean;

public class RemoteHandlerFactoryBean<T> implements FactoryBean<T> {

    private final Class<T> mapperInterface;

    public RemoteHandlerFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
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
}
