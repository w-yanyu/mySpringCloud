package com.example.remote.config;

import com.example.remote.InvocationHandler.RemoteChannelHandler;
import com.example.remote.annotation.RemoteChannel;
import com.example.remote.interfaces.RemoteProxyEnhancer;
import com.example.remote.manager.RemoteEnhancerManager;
import com.example.remote.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class RemoteChannelProxyFactory implements InvocationHandler {

    private RemoteChannelHandler handler;

    private final RemoteEnhancerManager enhancerManager;

    private Class<?> remoteChannelClass;

    public RemoteChannelProxyFactory(RemoteEnhancerManager enhancerManager) {
        this.enhancerManager = enhancerManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RemoteChannel remoteChannel = remoteChannelClass.getAnnotation(RemoteChannel.class);
        String systemId = StringUtils.isEmpty(remoteChannel.systemId())? remoteChannelClass.getSimpleName():remoteChannel.systemId();
        String channel = StringUtils.determineBeanNameFromClass(systemId);
        List<RemoteProxyEnhancer> enhancers = enhancerManager.getEnhancers(channel, method.getName());
        for (RemoteProxyEnhancer enhancer : enhancers) {
            enhancer.before(channel, method.getName(),proxy, method, args);
        }

        Object result = handler.invoke(proxy, method, args);

        for (int i = enhancers.size() - 1; i >= 0; i--) {
            result = enhancers.get(i).after(channel, method.getName(),proxy, method, args, result);
        }
        return result;
    }

    public Class<?> getRemoteChannelClass() {
        return remoteChannelClass;
    }

    public void setRemoteChannelClass(Class<?> remoteChannelClass) {
        this.remoteChannelClass = remoteChannelClass;
    }

    public RemoteChannelHandler getHandler() {
        return handler;
    }

    public void setHandler(RemoteChannelHandler handler) {
        this.handler = handler;
    }
}
