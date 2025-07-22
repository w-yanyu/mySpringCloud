package org.remote.service;


import org.remote.annotation.RemoteChannel;
import org.remote.config.ChannelConfig;
import org.remote.config.Config;
import org.remote.config.RemoteChannelProxyFactory;
import org.remote.constant.PropertiesConstants;
import org.remote.invocationHandler.RemoteChannelHandler;
import org.remote.registrar.EnhancerRegistrar;
import org.remote.registrar.impl.DefaultEnhancerRegistrar;
import org.remote.util.StringUtils;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Remote {

    public static <T> T getInstance(Class<T> channelClass) {
        return RemoteFactory.get().getInstance(channelClass);
    }

    public static class RemoteFactory {
        private static final RemoteFactory instance = new RemoteFactory();
        private final Map<String, Object> MAP = new HashMap<>();
        private Map<String, RemoteChannelHandler> handlers = ChannelConfig.get();
        private EnhancerRegistrar enhancerRegistrar;

        {
            String enhancerRegistryClassName = Config.get(PropertiesConstants.ENHANCER_REGISTRAR_IMPL, DefaultEnhancerRegistrar.class.getName());
            try {
                Class<?> aClass = Class.forName(enhancerRegistryClassName);
                if (EnhancerRegistrar.class.isAssignableFrom(aClass)) {
                    enhancerRegistrar = (EnhancerRegistrar) aClass.newInstance();
                } else {
                    throw new RuntimeException(enhancerRegistryClassName + ",The interface EnhancerRegistry implementation was not found.");
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        private RemoteFactory() {
        }

        public static RemoteFactory get() {
            return instance;
        }

        public <T> T getInstance(Class<T> channelClass) {
            return getInstance(channelClass, false);
        }

        @SuppressWarnings("unchecked")
        public <T> T getInstance(Class<T> channelClass, boolean nullException) {
            String className = channelClass.getName();
            if (!channelClass.isInterface()) {
                if (nullException) {
                    throw new IllegalArgumentException(className + " is not an interface");
                }
            }
            if (!channelClass.isAnnotationPresent(RemoteChannel.class)) {
                if (nullException) {
                    throw new IllegalArgumentException(className + " is not annotated with @RemoteChannel");
                }
            }

            RemoteChannel remoteChannel = channelClass.getAnnotation(RemoteChannel.class);
            String name = StringUtils.determineBeanNameFromClass(channelClass, remoteChannel.value());
            if (MAP.get(name) == null) {
                MAP.put(name, invoke(channelClass.getClassLoader(), channelClass));
            }
            return (T) MAP.get(name);
        }

        @SuppressWarnings("unchecked")
        public <T> T invoke(ClassLoader loader, Class<T> channelClass) {
            RemoteChannelProxyFactory remoteChannelProxyFactory = RemoteChannelProxyFactory.getInstance();
            remoteChannelProxyFactory.setHandlers(handlers);
            remoteChannelProxyFactory.setEnhancerManager(enhancerRegistrar.register());
            remoteChannelProxyFactory.setRemoteChannelClass(channelClass);
            return (T) Proxy.newProxyInstance(loader, new Class[]{channelClass}, remoteChannelProxyFactory);
        }

        public void setEnhancerRegistrar(EnhancerRegistrar enhancerRegistrar) {
            this.enhancerRegistrar = enhancerRegistrar;
        }

        public void setHandlers(Map<String, RemoteChannelHandler> handlers) {
            this.handlers = handlers;
        }
    }
}
