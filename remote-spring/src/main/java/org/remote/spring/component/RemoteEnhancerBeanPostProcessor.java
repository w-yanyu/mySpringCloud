package org.remote.spring.component;

import org.remote.annotation.RemoteEnhancer;
import org.remote.enhancer.RemoteProxyEnhancer;
import org.remote.spring.enhancer.RemoteEnhancerRegistrar;
import org.remote.spring.util.AnnotationUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class RemoteEnhancerBeanPostProcessor implements BeanPostProcessor, Ordered {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (AnnotationUtils.implementsInterfaceAndHasAnnotation(bean.getClass(), RemoteProxyEnhancer.class, RemoteEnhancer.class)) {
            RemoteEnhancerRegistrar enhancerRegistrar = RemoteEnhancerRegistrar.getInstance();
            enhancerRegistrar.add((RemoteProxyEnhancer) bean);
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}