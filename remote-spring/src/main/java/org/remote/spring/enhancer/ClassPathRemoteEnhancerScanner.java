package org.remote.spring.enhancer;

import org.remote.annotation.RemoteEnhancer;
import org.remote.enhancer.RemoteProxyEnhancer;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Objects;
import java.util.Set;

public class ClassPathRemoteEnhancerScanner extends ClassPathBeanDefinitionScanner {

    private final Class<? extends RemoteEnhancerFactoryBean> remoteEnhancerFactoryBeanClass = RemoteEnhancerFactoryBean.class;

    public ClassPathRemoteEnhancerScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        if (!beanDefinitions.isEmpty()) {
            this.processBeanDefinitions(beanDefinitions);
        }
        return beanDefinitions;
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        BeanDefinitionRegistry registry = this.getRegistry();
        for (BeanDefinitionHolder holder : beanDefinitions) {
            AbstractBeanDefinition definition = (AbstractBeanDefinition) holder.getBeanDefinition();
            String beanClassName = definition.getBeanClassName();
            Class<?> beanClass;
            try {
                beanClass = Class.forName(beanClassName);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            definition.getConstructorArgumentValues().addGenericArgumentValue(Objects.requireNonNull(beanClassName));
            definition.getPropertyValues().add("mapperInterface", beanClass);
            definition.setBeanClass(this.remoteEnhancerFactoryBeanClass);
            definition.setAttribute("factoryBeanObjectType", beanClassName);
            definition.setAutowireMode(2);
            if (!definition.isSingleton()) {
                BeanDefinitionHolder proxyHolder = ScopedProxyUtils.createScopedProxy(holder, registry, true);
                if (registry.containsBeanDefinition(proxyHolder.getBeanName())) {
                    registry.removeBeanDefinition(proxyHolder.getBeanName());
                }

                registry.registerBeanDefinition(proxyHolder.getBeanName(), proxyHolder.getBeanDefinition());
            }
        }
    }

    public void registerFilters() {
        this.addIncludeFilter((metadataReader, metadataReaderFactory) -> {
            return true;
        });
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        String[] interfaceNames = metadata.getInterfaceNames();
        for (String interfaceName : interfaceNames) {
            if (RemoteProxyEnhancer.class.getName().equals(interfaceName) && metadata.hasAnnotation(RemoteEnhancer.class.getName())) {
                return true;
            }
        }
        return false;
    }
}
