import com.example.remote.invocationHandler.RemoteChannelHandler;

import java.lang.reflect.Method;

public class TestRemoteChannelHandler implements RemoteChannelHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("test ..." + method);
        return new Object();
    }
}