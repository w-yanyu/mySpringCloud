package com.example.remote.service;

import com.example.remote.annotation.RemoteChannel;
import com.example.remote.config.Config;
import com.example.remote.config.RemoteChannelProxyFactory;
import com.example.remote.enums.RemoteCallWay;
import com.example.remote.invocationHandler.RemoteChannelHandler;
import com.example.remote.registry.DefaultEnhancerRegistry;
import com.example.remote.util.StringUtils;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Remote {

    private static final Remote instance = new Remote();
    private static final Map<String, Object> MAP = new HashMap<>();

    private Remote() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> channelClass) {
        String className = channelClass.getName();
        if (!channelClass.isInterface()) {
            throw new IllegalArgumentException(className + " is not an interface");
        }
        if (!channelClass.isAnnotationPresent(RemoteChannel.class)) {
            throw new IllegalArgumentException(className + " is not annotated with @RemoteChannel");
        }

        RemoteChannel remoteChannel = channelClass.getAnnotation(RemoteChannel.class);
        String name = StringUtils.determineBeanNameFromClass(channelClass, remoteChannel.value());
        if (MAP.get(name) == null) {
            MAP.put(name, instance.invoke(channelClass));
        }
        return (T) MAP.get(name);
    }

    @SuppressWarnings("unchecked")
    private <O> O invoke(Class<O> channelClass) {
        RemoteCallWay remoteCallWay = Enum.valueOf(RemoteCallWay.class, Config.get("remote.channel.handler", "netty").toUpperCase());
        RemoteChannelHandler handler = remoteCallWay.getHandler();
        return (O) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{channelClass}, new RemoteChannelProxyFactory(handler, new DefaultEnhancerRegistry().register(), channelClass));
    }

}
