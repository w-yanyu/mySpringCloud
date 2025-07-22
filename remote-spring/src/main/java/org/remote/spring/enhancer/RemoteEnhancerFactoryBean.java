package org.remote.spring.enhancer;

import org.remote.annotation.RemoteEnhancer;
import org.remote.enhancer.RemoteProxyEnhancer;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Constructor;

public class RemoteEnhancerFactoryBean<T> implements FactoryBean<T> {

    private final Class<T> mapperInterface;

    public RemoteEnhancerFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public static boolean registerCondition(Class<?> clazz) {
        return clazz.isAnnotationPresent(RemoteEnhancer.class) && RemoteProxyEnhancer.class.isAssignableFrom(clazz);
    }

    @Override
    public T getObject() throws Exception {
        if (!registerCondition(mapperInterface)) {
            return null;
        }
        Constructor<T> constructor = mapperInterface.getConstructor();
        return constructor.newInstance();
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
