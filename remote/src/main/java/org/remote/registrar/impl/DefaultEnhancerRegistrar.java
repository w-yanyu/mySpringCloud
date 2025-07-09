package org.remote.registrar.impl;


import org.remote.enhancer.RemoteProxyEnhancer;
import org.remote.manager.RemoteEnhancerManager;
import org.remote.manager.impl.DefaultRemoteEnhancerManager;
import org.remote.registrar.EnhancerRegistrar;

import java.util.ServiceLoader;

public class DefaultEnhancerRegistrar implements EnhancerRegistrar {

    @Override
    public RemoteEnhancerManager getEnhancerManager() {
        return DefaultRemoteEnhancerManager.getInstance();
    }

    @Override
    public Iterable<RemoteProxyEnhancer> getIterable() {
        return ServiceLoader.load(RemoteProxyEnhancer.class);
    }

}
