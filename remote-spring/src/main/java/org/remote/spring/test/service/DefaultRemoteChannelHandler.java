package org.remote.spring.test.service;

import org.remote.invocationHandler.RemoteChannelHandler;
import org.remote.spring.annotation.RemoteHandler;

import java.lang.reflect.Method;

@RemoteHandler(channels = {"pudao", "bairong"})
public class DefaultRemoteChannelHandler implements RemoteChannelHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName()+",common Handler ...>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return new Object();
    }
}