package org.remote.config;


import org.remote.annotation.RemoteChannel;
import org.remote.annotation.RemoteServiceCode;
import org.remote.enhancer.RemoteProxyEnhancer;
import org.remote.invocationHandler.RemoteChannelHandler;
import org.remote.invocationHandler.impl.netty.NettyRemoteChannelHandler;
import org.remote.manager.RemoteEnhancerManager;
import org.remote.util.AnnotationUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class RemoteChannelProxyFactory implements InvocationHandler {

    private static final RemoteChannelProxyFactory instance = new RemoteChannelProxyFactory();

    private RemoteChannelProxyFactory() {
    }

    private Map<String, RemoteChannelHandler> handlers;

    private RemoteEnhancerManager enhancerManager;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String channel = AnnotationUtils.getBeanNameFromClass(method.getDeclaringClass(), RemoteChannel.class, "value");
        String methodName = AnnotationUtils.getMethodNameFromAnnotation(method, RemoteServiceCode.class, "value");
        RemoteChannelHandler handler = handlers.get(channel) == null ? new NettyRemoteChannelHandler() : handlers.get(channel);
        List<RemoteProxyEnhancer> enhancers = enhancerManager.getEnhancers(channel, methodName);
        for (RemoteProxyEnhancer enhancer : enhancers) {
            Object before = enhancer.before(channel, methodName, proxy, method, args);
        }

        Object result = handler == null ? null : handler.invoke(proxy, method, args);

        for (int i = enhancers.size() - 1; i >= 0; i--) {
            result = enhancers.get(i).after(channel, methodName, proxy, method, args, result);
        }
        return result;
    }

    public RemoteChannelProxyFactory(Map<String, RemoteChannelHandler> handlers, RemoteEnhancerManager enhancerManager) {
        this.handlers = handlers;
        this.enhancerManager = enhancerManager;
    }

    public void setEnhancerManager(RemoteEnhancerManager enhancerManager) {
        this.enhancerManager = enhancerManager;
    }

    public static RemoteChannelProxyFactory getInstance() {
        return instance;
    }

    public Map<String, RemoteChannelHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(Map<String, RemoteChannelHandler> handlers) {
        this.handlers = handlers;
    }

    public RemoteEnhancerManager getEnhancerManager() {
        return enhancerManager;
    }

}
