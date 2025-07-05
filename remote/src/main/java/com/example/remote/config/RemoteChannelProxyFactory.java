package com.example.remote.config;

import com.example.remote.annotation.RemoteChannel;
import com.example.remote.annotation.RemoteServiceCode;
import com.example.remote.enhancer.RemoteProxyEnhancer;
import com.example.remote.invocationHandler.RemoteChannelHandler;
import com.example.remote.manager.RemoteEnhancerManager;
import com.example.remote.util.AnnotationUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class RemoteChannelProxyFactory implements InvocationHandler {

    private RemoteChannelHandler handler;

    private RemoteEnhancerManager enhancerManager;

    private Class<?> remoteChannelClass;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String channel = AnnotationUtils.getBeanNameFromClass(remoteChannelClass, RemoteChannel.class, "value");
        String methodName = AnnotationUtils.getMethodNameFromAnnotation(method, RemoteServiceCode.class, "value");
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

    public RemoteChannelProxyFactory() {
    }

    public RemoteChannelProxyFactory(RemoteChannelHandler handler, RemoteEnhancerManager enhancerManager, Class<?> remoteChannelClass) {
        this.handler = handler;
        this.enhancerManager = enhancerManager;
        this.remoteChannelClass = remoteChannelClass;
    }

    public void setHandler(RemoteChannelHandler handler) {
        this.handler = handler;
    }

    public void setEnhancerManager(RemoteEnhancerManager enhancerManager) {
        this.enhancerManager = enhancerManager;
    }

    public void setRemoteChannelClass(Class<?> remoteChannelClass) {
        this.remoteChannelClass = remoteChannelClass;
    }
}
