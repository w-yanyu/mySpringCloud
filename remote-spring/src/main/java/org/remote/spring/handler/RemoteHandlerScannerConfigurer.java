package org.remote.spring.handler;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class RemoteHandlerScannerConfigurer implements BeanDefinitionRegistryPostProcessor {

    private String[] basePackages;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathRemoteHandlerScanner scanner = new ClassPathRemoteHandlerScanner(registry);
        scanner.scan(this.basePackages);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    public void setBasePackages(String[] basePackages) {
        this.basePackages = basePackages;
    }

}
