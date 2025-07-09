package org.remote.spring.enhancer;

import org.remote.enhancer.RemoteProxyEnhancer;
import org.remote.manager.RemoteEnhancerManager;
import org.remote.manager.impl.DefaultRemoteEnhancerManager;
import org.remote.registrar.EnhancerRegistrar;

import java.util.ServiceLoader;

public class RemoteEnhancerRegistrar implements EnhancerRegistrar {

    @Override
    public RemoteEnhancerManager getEnhancerManager() {
        return DefaultRemoteEnhancerManager.getInstance();
    }

    @Override
    public Iterable<RemoteProxyEnhancer> getIterable() {

        // TODO 写个实现
        return ServiceLoader.load(RemoteProxyEnhancer.class);
    }

}
