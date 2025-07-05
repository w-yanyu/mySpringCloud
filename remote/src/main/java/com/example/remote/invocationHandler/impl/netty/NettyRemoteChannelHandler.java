package com.example.remote.invocationHandler.impl.netty;

import com.example.remote.invocationHandler.RemoteChannelHandler;

import java.lang.reflect.Method;

public class NettyRemoteChannelHandler implements RemoteChannelHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return new Object();
    }
}
