package org.remote.invocationHandler.impl.http;


import org.remote.invocationHandler.RemoteChannelHandler;

import java.lang.reflect.Method;

public class HttpRemoteChannelHandler implements RemoteChannelHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return new Object();
    }
}
