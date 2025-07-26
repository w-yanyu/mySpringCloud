package org.remote.spring.channel;

import org.remote.annotation.RemoteChannel;
import org.remote.annotation.RemoteEnhancer;
import org.remote.enhancer.RemoteProxyEnhancer;
import org.remote.invocationHandler.RemoteChannelHandler;
import org.remote.service.Remote;
import org.remote.spring.annotation.RemoteHandler;
import org.remote.spring.enhancer.RemoteEnhancerRegistrar;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

public class RemoteChannelFactoryBean<T> implements FactoryBean<T>, ApplicationContextAware {
    private Class<T> mapperInterface;

    private final Remote.RemoteFactory remoteFactory = Remote.RemoteFactory.get();

    public RemoteChannelFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public static boolean registerCondition(Class<?> clazz) {
        return clazz.isAnnotationPresent(RemoteChannel.class);
    }

    @Override
    public T getObject() {
        if (!registerCondition(mapperInterface)) {
            throw new IllegalArgumentException("代理接口类型异常！");
        }

        return remoteFactory.getInstance(mapperInterface, true);
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        initializeHandlersFromContext(applicationContext);
        initializeEnhancersFromContext(applicationContext);
    }

    private void initializeHandlersFromContext(ApplicationContext applicationContext) {
        Map<String, RemoteChannelHandler> handlerBeanMap = applicationContext.getBeansOfType(RemoteChannelHandler.class);
        Map<String, RemoteChannelHandler> handlers = new HashMap<>();
        handlerBeanMap.forEach((k, v) -> {
            Class<? extends RemoteChannelHandler> handlerClass = v.getClass();
            if (handlerClass.isAnnotationPresent(RemoteHandler.class)) {
                RemoteHandler annotation = handlerClass.getAnnotation(RemoteHandler.class);
                String[] channels = annotation.channels();
                for (String channel : channels) {
                    handlers.put(channel, v);
                }
            }
        });
        remoteFactory.setHandlers(handlers);
    }

    private void initializeEnhancersFromContext(ApplicationContext applicationContext) {
        Map<String, RemoteProxyEnhancer> handlerBeanMap = applicationContext.getBeansOfType(RemoteProxyEnhancer.class);
        RemoteEnhancerRegistrar enhancerRegistrar = RemoteEnhancerRegistrar.getInstance();
        handlerBeanMap.forEach((k, v) -> {
            if (v.getClass().isAnnotationPresent(RemoteEnhancer.class)) {
                enhancerRegistrar.add(v);
            }
        });
        remoteFactory.setEnhancerRegistrar(enhancerRegistrar);
    }
}