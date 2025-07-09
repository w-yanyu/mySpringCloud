package org.remote.spring.enhancer;

import org.remote.spring.channel.ClassPathRemoteChannelScanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class RemoteEnhancerScannerConfigurer implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private String[] basePackages;

    private ApplicationContext applicationContext;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathRemoteChannelScanner scanner = new ClassPathRemoteChannelScanner(registry);
        scanner.scan(this.basePackages);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    public void setBasePackages(String[] basePackages) {
        this.basePackages = basePackages;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}