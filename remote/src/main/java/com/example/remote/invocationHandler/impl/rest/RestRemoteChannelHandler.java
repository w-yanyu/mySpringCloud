package com.example.remote.invocationHandler.impl.rest;

import com.example.remote.invocationHandler.RemoteChannelHandler;

import java.lang.reflect.Method;

public class RestRemoteChannelHandler implements RemoteChannelHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return new Object();
    }
}
