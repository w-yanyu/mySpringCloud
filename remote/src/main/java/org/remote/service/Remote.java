package org.remote.service;


import org.remote.annotation.RemoteChannel;
import org.remote.config.ChannelConfig;
import org.remote.config.Config;
import org.remote.config.RemoteChannelProxyFactory;
import org.remote.constant.PropertiesConstants;
import org.remote.registrar.EnhancerRegistrar;
import org.remote.registrar.impl.DefaultEnhancerRegistrar;
import org.remote.util.StringUtils;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Remote {

    private static final Map<String, Object> MAP = new HashMap<>();

    private Remote() {
    }

    public static <T> T getInstance(Class<T> channelClass) {
        return getInstance(channelClass, false);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> channelClass, boolean nullException) {
        String className = channelClass.getName();
        if (!channelClass.isInterface()) {
            if (nullException) {
                throw new IllegalArgumentException(className + " is not an interface");
            } else {
                return null;
            }
        }
        if (!channelClass.isAnnotationPresent(RemoteChannel.class)) {
            if (nullException) {
                throw new IllegalArgumentException(className + " is not annotated with @RemoteChannel");
            } else {
                return null;
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
    public static <T> T invoke(ClassLoader loader, Class<T> channelClass) {
        String enhancerRegistryClassName = Config.get(PropertiesConstants.ENHANCER_REGISTRAR_IMPL, DefaultEnhancerRegistrar.class.getName());
        EnhancerRegistrar enhancerRegistrar;
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
        return (T) Proxy.newProxyInstance(loader, new Class[]{channelClass}, new RemoteChannelProxyFactory(ChannelConfig.get(), enhancerRegistrar.register(), channelClass));
    }

}
