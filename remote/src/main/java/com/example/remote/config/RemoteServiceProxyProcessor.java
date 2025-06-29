package com.example.remote.config;

import com.example.remote.annotations.RemoteChannel;
import com.example.remote.annotations.ServiceCode;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class RemoteServiceProxyProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RemoteChannel.class)) {
            return createProxy(bean);
        }
        return bean;
    }

    private Object createProxy(Object bean) {
        return Proxy.newProxyInstance(
                bean.getClass().getClassLoader(),
                bean.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    if (method.isAnnotationPresent(ServiceCode.class)) {
                        ServiceCode serviceCode = method.getAnnotation(ServiceCode.class);
                        String systemId = bean.getClass().getAnnotation(RemoteChannel.class).system();
                        String serviceCodeValue = serviceCode.value();
                        callThirdPartySystem(systemId, serviceCodeValue, args);
                    }

                    // return method.invoke(bean, args);
                    return null;
                }

        );
    }

    private void callThirdPartySystem(String systemId, String serviceCodeValue, Object[] args) {
        System.out.println("Calling third-party system with systemId: " + systemId + ", serviceCode: " + serviceCodeValue);
    }
}
