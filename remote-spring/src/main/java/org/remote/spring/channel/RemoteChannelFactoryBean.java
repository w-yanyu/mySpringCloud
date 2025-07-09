package org.remote.spring.channel;

import org.remote.service.Remote;
import org.springframework.beans.factory.FactoryBean;

public class RemoteChannelFactoryBean<T> implements FactoryBean<T> {

    private final Class<T> mapperInterface;

    public RemoteChannelFactoryBean(Class<T> mapperInterface) {
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
