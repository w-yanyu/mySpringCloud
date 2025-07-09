package org.remote.invocationHandler.impl.rest;


import org.remote.invocationHandler.RemoteChannelHandler;

import java.lang.reflect.Method;

public class RestRemoteChannelHandler implements RemoteChannelHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return new Object();
    }
}
