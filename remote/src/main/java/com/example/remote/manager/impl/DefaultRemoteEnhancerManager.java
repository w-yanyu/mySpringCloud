package com.example.remote.manager.impl;

import com.example.remote.annotation.RemoteEnhancer;
import com.example.remote.constant.SymbolConstants;
import com.example.remote.interfaces.RemoteProxyEnhancer;
import com.example.remote.manager.RemoteEnhancerManager;
import com.example.remote.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultRemoteEnhancerManager implements RemoteEnhancerManager {

    private static final DefaultRemoteEnhancerManager instance = new DefaultRemoteEnhancerManager();

    public static final String WILDCARD = SymbolConstants.WILDCARD;

    private static final String DELIMITER = SymbolConstants.WILDCARD;

    private DefaultRemoteEnhancerManager (){}

    public static DefaultRemoteEnhancerManager getInstance(){
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
        Assert.hasLength(channel, "parameter channel is null");
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
        Assert.hasLength(channel, "parameter channel is null");
        Assert.hasLength(method, "parameter method is null");

        String key = channel + DELIMITER + method;
        return methodEnhancerMap.computeIfAbsent(key, k -> {
            Set<RemoteProxyEnhancer> enhancers = new HashSet<>(generalEnhancer);
            enhancers.addAll(getEnhancersForMethod(channel, WILDCARD));
            enhancers.addAll(getEnhancersForMethod(channel, method));

            return enhancers.stream()
                    .sorted(Comparator.comparingInt(this::getEnhancerOrder))
                    .collect(Collectors.toList());
        });
    }

    private List<RemoteProxyEnhancer> getEnhancersForMethod(String channel, String method) {
        return enhancerMap.getOrDefault(channel, Collections.emptyMap())
                .getOrDefault(method, Collections.emptyList());
    }

    private int getEnhancerOrder(RemoteProxyEnhancer enhancer) {
        RemoteEnhancer annotation = enhancer.getClass().getAnnotation(RemoteEnhancer.class);
        return annotation.order();
    }
}
