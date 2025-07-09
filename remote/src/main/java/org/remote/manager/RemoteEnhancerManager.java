package org.remote.manager;


import org.remote.enhancer.RemoteProxyEnhancer;

import java.util.List;

public interface RemoteEnhancerManager {

    /**
     * 注册增强器
     *
     * @param channel
     * @param method
     * @param enhancer
     * @param order
     */
    void registerEnhancer(String channel, String method, RemoteProxyEnhancer enhancer, int order);

    /**
     * 获取指定渠道和方法的增强器，并根据 order 排序
     *
     * @param channel
     * @param method
     * @return
     */
    List<RemoteProxyEnhancer> getEnhancers(String channel, String method);
}
