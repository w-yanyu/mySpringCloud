package com.example.remote.interfaces.impl;

import com.example.remote.annotation.RemoteEnhancer;
import com.example.remote.interfaces.RemoteProxyEnhancer;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
@RemoteEnhancer
public class LogRemoteProxyEnhancer implements RemoteProxyEnhancer {

    @Override
    public Object before(String channel, String methodName, Object proxy, Method method, Object[] args) {
        log.info("channel:{}",channel);
        log.info("methodName:{}",methodName);
        log.info("parameter:{}",args);
        return null;
    }

    @Override
    public Object after(String channel, String methodName, Object proxy, Method method, Object[] args, Object result) {
        log.info("channel:{}",channel);
        log.info("methodName:{}",methodName);
        log.info("parameter:{}",args);
        log.info("result:{}",result);
        return null;
    }
}
