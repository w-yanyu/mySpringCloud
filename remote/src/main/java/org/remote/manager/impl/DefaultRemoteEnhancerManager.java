package org.remote.manager.impl;



import org.remote.annotation.RemoteEnhancer;
import org.remote.constant.SymbolConstants;
import org.remote.enhancer.RemoteProxyEnhancer;
import org.remote.manager.RemoteEnhancerManager;
import org.remote.util.Assert;

import java.util.*;

public class DefaultRemoteEnhancerManager implements RemoteEnhancerManager {

    private static final DefaultRemoteEnhancerManager instance = new DefaultRemoteEnhancerManager();

    public static final String WILDCARD = SymbolConstants.WILDCARD;

    private static final String DELIMITER = SymbolConstants.WILDCARD;

    private DefaultRemoteEnhancerManager() {
    }

    public static DefaultRemoteEnhancerManager getInstance() {
        return instance;
    }

    /**
     * 存储渠道 -> 方法 -> 拦截器，通用方法使用 "#" 作为标识
     */
    private final Map<String, Map<String, List<RemoteProxyEnhancer>>> enhancerMap = new HashMap<>();

    private final Set<RemoteProxyEnhancer> generalEnhancer = new HashSet<>();

    private final Map<String, List<RemoteProxyEnhancer>> methodEnhancerMap = new HashMap<>();

    @Override
    public void registerEnhancer(String channel, String method, RemoteProxyEnhancer enhancer, int order) {
        Assert.hasLength(channel, "parameter channel is empty");
        if (WILDCARD.equals(channel)) {
            generalEnhancer.add(enhancer);
            return;
        }

        String key = method == null || method.isEmpty() ? WILDCARD : method;

        enhancerMap.computeIfAbsent(channel, k -> new HashMap<>())
                .computeIfAbsent(key, k -> new ArrayList<>())
                .add(enhancer);
    }

    @Override
    public List<RemoteProxyEnhancer> getEnhancers(String channel, String method) {
        Assert.hasLength(channel, "parameter channel is empty");
        Assert.hasLength(method, "parameter method is empty");

        String key = channel + DELIMITER + method;
        return methodEnhancerMap.computeIfAbsent(key, k -> {
            Set<RemoteProxyEnhancer> enhancers = new TreeSet<>(Comparator.comparingInt(this::getEnhancerOrder));
            enhancers.addAll(generalEnhancer);
            enhancers.addAll(getEnhancersForMethod(channel, WILDCARD));
            enhancers.addAll(getEnhancersForMethod(channel, method));
            return new ArrayList<>(enhancers);
        });
    }

    private List<RemoteProxyEnhancer> getEnhancersForMethod(String channel, String method) {
        return enhancerMap.getOrDefault(channel, Collections.emptyMap())
                .getOrDefault(method, Collections.emptyList());
    }

    private int getEnhancerOrder(RemoteProxyEnhancer enhancer) {
        Class<? extends RemoteProxyEnhancer> clazz = enhancer.getClass();
        if (!clazz.isAnnotationPresent(RemoteEnhancer.class)) {
            throw new IllegalArgumentException(clazz.getName() + " is not annotated with @RemoteChannel");
        }
        RemoteEnhancer remoteEnhancer = clazz.getAnnotation(RemoteEnhancer.class);
        return remoteEnhancer.order();
    }

}
