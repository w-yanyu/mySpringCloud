package org.remote.enhancer;

import java.lang.reflect.Method;

public interface RemoteProxyEnhancer {

    Object before(String channel, String methodName,Object proxy, Method method, Object[] args);

    Object after(String channel, String methodName,Object proxy, Method method, Object[] args, Object result);
}
