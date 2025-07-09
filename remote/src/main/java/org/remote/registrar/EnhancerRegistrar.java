package org.remote.registrar;

import org.remote.annotation.RemoteEnhancer;
import org.remote.enhancer.RemoteProxyEnhancer;
import org.remote.manager.RemoteEnhancerManager;

public interface EnhancerRegistrar {

    RemoteEnhancerManager getEnhancerManager();

    Iterable<RemoteProxyEnhancer> getIterable();

    default RemoteEnhancerManager register() {
        RemoteEnhancerManager enhancerManager = getEnhancerManager();
        for (RemoteProxyEnhancer enhancer : getIterable()) {
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
