package org.remote.spring.component;

import org.remote.config.ChannelConfig;
import org.remote.invocationHandler.RemoteChannelHandler;
import org.remote.spring.annotation.RemoteHandler;
import org.remote.spring.util.AnnotationUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class RemoteHandlerBeanPostProcessor implements BeanPostProcessor, Ordered {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (AnnotationUtils.implementsInterfaceAndHasAnnotation(beanClass, RemoteChannelHandler.class, RemoteHandler.class)) {
            RemoteHandler annotation = beanClass.getAnnotation(RemoteHandler.class);
            String[] channels = annotation.channels();
            if (channels != null) {
                for (String channel : channels) {
                    ChannelConfig.put(channel, (RemoteChannelHandler) bean);
                }
            }
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}