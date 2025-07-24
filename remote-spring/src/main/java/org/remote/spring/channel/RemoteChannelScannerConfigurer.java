package org.remote.spring.channel;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class RemoteChannelScannerConfigurer implements BeanDefinitionRegistryPostProcessor {
    private String[] basePackages;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathRemoteChannelScanner scanner = new ClassPathRemoteChannelScanner(registry);
        scanner.registerFilters();
        scanner.scan(this.basePackages);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    public void setBasePackages(String[] basePackages) {
        this.basePackages = basePackages;
    }

}
