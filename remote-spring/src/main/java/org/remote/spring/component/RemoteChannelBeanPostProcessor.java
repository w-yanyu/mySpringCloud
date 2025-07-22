package org.remote.spring.component;


import org.remote.registrar.EnhancerRegistrar;
import org.remote.service.Remote;
import org.remote.spring.channel.ChannelBeanFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

@Component
public class RemoteChannelBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private final Remote.RemoteFactory remoteFactory = Remote.RemoteFactory.get();

    private final Set<Class<?>> channelClassSet = ChannelBeanFactory.getInstance().getChannelClassSet();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Iterator<Class<?>> iterator = channelClassSet.iterator();
        while (iterator.hasNext()) {
            Class<?> channelClass = iterator.next();
            if (beanClass.isAssignableFrom(channelClass)) {
                EnhancerRegistrar beanRegistrar = applicationContext.getBean(EnhancerRegistrar.class);
                remoteFactory.setEnhancerRegistrar(beanRegistrar);
                return remoteFactory.getInstance(beanClass);
            }
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}