package org.remote.invocationHandler.impl.customize;


import org.remote.invocationHandler.RemoteChannelHandler;
import org.remote.invocationHandler.impl.netty.NettyRemoteChannelHandler;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.ServiceLoader;

public class CustomizeRemoteChannelHandler implements RemoteChannelHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ServiceLoader<RemoteChannelHandler> load = ServiceLoader.load(RemoteChannelHandler.class);
        Iterator<RemoteChannelHandler> iterator = load.iterator();
        RemoteChannelHandler remoteChannelHandler = iterator.hasNext() ? iterator.next() : new NettyRemoteChannelHandler();
        return remoteChannelHandler.invoke(proxy, method, args);
    }
}
