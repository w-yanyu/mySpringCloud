package com.example.remote.registry;

import com.example.remote.annotation.RemoteEnhancer;
import com.example.remote.enhancer.RemoteProxyEnhancer;
import com.example.remote.manager.RemoteEnhancerManager;
import com.example.remote.manager.impl.DefaultRemoteEnhancerManager;

import java.util.ServiceLoader;

public class DefaultEnhancerRegistry {

    public RemoteEnhancerManager register() {
        DefaultRemoteEnhancerManager enhancerManager = DefaultRemoteEnhancerManager.getInstance();
        ServiceLoader<RemoteProxyEnhancer> enhancerServiceLoader = ServiceLoader.load(RemoteProxyEnhancer.class);
        for (RemoteProxyEnhancer enhancer : enhancerServiceLoader) {
            if (!enhancer.getClass().isAnnotationPresent(RemoteEnhancer.class)) {
                continue;
            }
            RemoteEnhancer annotation = enhancer.getClass().getAnnotation(RemoteEnhancer.class);
            String[] methods = annotation.methods();
            for (String method : methods) {
                enhancerManager.registerEnhancer(annotation.value(), method, enhancer, annotation.order());
            }
        }
        return enhancerManager;
    }
}
