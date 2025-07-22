package org.remote.spring.enhancer;

import org.remote.enhancer.RemoteProxyEnhancer;
import org.remote.manager.RemoteEnhancerManager;
import org.remote.manager.impl.DefaultRemoteEnhancerManager;
import org.remote.registrar.EnhancerRegistrar;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RemoteEnhancerRegistrar implements EnhancerRegistrar {
    private static final RemoteEnhancerRegistrar instance = new RemoteEnhancerRegistrar();
    private static final Set<RemoteProxyEnhancer> SET = new HashSet<>();

    private RemoteEnhancerRegistrar() {
    }

    public static RemoteEnhancerRegistrar getInstance() {
        return instance;
    }

    public void add(RemoteProxyEnhancer remoteProxyEnhancer) {
        SET.add(remoteProxyEnhancer);
    }

    public boolean addAll(Collection<? extends RemoteProxyEnhancer> c) {
        return SET.addAll(c);
    }

    @Override
    public RemoteEnhancerManager getEnhancerManager() {
        return DefaultRemoteEnhancerManager.getInstance();
    }

    @Override
    public Iterable<RemoteProxyEnhancer> getIterable() {
        return SET;
    }

}
