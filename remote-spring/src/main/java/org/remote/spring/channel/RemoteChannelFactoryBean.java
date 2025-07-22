package org.remote.spring.channel;

import org.remote.annotation.RemoteChannel;
import org.remote.enhancer.RemoteProxyEnhancer;
import org.remote.invocationHandler.RemoteChannelHandler;
import org.remote.registrar.EnhancerRegistrar;
import org.remote.service.Remote;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RemoteChannelFactoryBean<T> implements FactoryBean<T>, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private final Remote.RemoteFactory remoteFactory = Remote.RemoteFactory.get();
    private final Class<T> mapperInterface;

    public RemoteChannelFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
        EnhancerRegistrar beanRegistrar = applicationContext.getBean(EnhancerRegistrar.class);
        remoteFactory.setEnhancerRegistrar(beanRegistrar);
    }

    public static boolean registerCondition(Class<?> clazz) {
        return clazz.isAnnotationPresent(RemoteChannel.class) && RemoteProxyEnhancer.class.isAssignableFrom(clazz);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getObject() throws Exception {
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
