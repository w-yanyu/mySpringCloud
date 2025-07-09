package org.remote.spring.channel;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Objects;
import java.util.Set;

public class ClassPathRemoteChannelScanner extends ClassPathBeanDefinitionScanner {

    private Class<? extends RemoteChannelFactoryBean> remoteChannelFactoryBeanClass = RemoteChannelFactoryBean.class;

    public ClassPathRemoteChannelScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        if (!beanDefinitions.isEmpty()){
            processBeanDefinitions(beanDefinitions);
        }
        return super.doScan(basePackages);
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions){
        BeanDefinitionRegistry registry = this.getRegistry();
        for (BeanDefinitionHolder holder : beanDefinitions) {
            AbstractBeanDefinition definition = (AbstractBeanDefinition)holder.getBeanDefinition();
            String beanClassName = definition.getBeanClassName();
            definition.getConstructorArgumentValues().addGenericArgumentValue(Objects.requireNonNull(beanClassName));
            definition.setBeanClass(this.remoteChannelFactoryBeanClass);
            if (!definition.isSingleton()) {
                BeanDefinitionHolder proxyHolder = ScopedProxyUtils.createScopedProxy(holder, registry, true);
                if (registry.containsBeanDefinition(proxyHolder.getBeanName())) {
                    registry.removeBeanDefinition(proxyHolder.getBeanName());
                }

                registry.registerBeanDefinition(proxyHolder.getBeanName(), proxyHolder.getBeanDefinition());
            }
        }
    }
}
