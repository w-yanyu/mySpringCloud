import org.remote.annotation.RemoteEnhancer;
import org.remote.enhancer.RemoteProxyEnhancer;

import java.lang.reflect.Method;
import java.util.Arrays;

@RemoteEnhancer(value = "testChannel002", methods = {"method02"})
public class TestLogRemoteProxyEnhancer implements RemoteProxyEnhancer {

    @Override
    public Object before(String channel, String methodName, Object proxy, Method method, Object[] args) {
        System.out.println("channel:" + channel);
        System.out.println("methodName:" + methodName);
        System.out.println("parameter:" + Arrays.toString(args));
        return null;
    }

    @Override
    public Object after(String channel, String methodName, Object proxy, Method method, Object[] args, Object result) {
        System.out.println("channel:" + channel);
        System.out.println("methodName:" + methodName);
        System.out.println("parameter:" + Arrays.toString(args));
        System.out.println("result:" + result);
        return null;
    }
}
