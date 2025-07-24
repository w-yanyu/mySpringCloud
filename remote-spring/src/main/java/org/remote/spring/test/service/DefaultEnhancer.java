package org.remote.spring.test.service;

import org.remote.annotation.RemoteEnhancer;
import org.remote.enhancer.RemoteProxyEnhancer;

import java.lang.reflect.Method;

@RemoteEnhancer
public class DefaultEnhancer implements RemoteProxyEnhancer {
    @Override
    public Object before(String channel, String methodName, Object proxy, Method method, Object[] args) {
        System.out.println(channel + ":" + methodName + "Enhancer before....");
        return null;
    }

    @Override
    public Object after(String channel, String methodName, Object proxy, Method method, Object[] args, Object result) {
        System.out.println(channel + ":" + methodName + "Enhancer after....");
        return null;
    }
}
