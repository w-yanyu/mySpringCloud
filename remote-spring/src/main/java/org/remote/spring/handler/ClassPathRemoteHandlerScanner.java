package org.remote.spring.handler;

import org.remote.enhancer.RemoteProxyEnhancer;
import org.remote.spring.annotation.RemoteHandler;
import org.remote.spring.channel.RemoteChannelFactoryBean;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;

public class ClassPathRemoteHandlerScanner extends ClassPathBeanDefinitionScanner {

    private Class<? extends RemoteChannelFactoryBean> remoteChannelFactoryBeanClass = RemoteChannelFactoryBean.class;

    public ClassPathRemoteHandlerScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

//    @Override
//    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
//        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
//        if (!beanDefinitions.isEmpty()) {
//            processBeanDefinitions(beanDefinitions);
//        }
//        return beanDefinitions;
//    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        BeanDefinitionRegistry registry = this.getRegistry();
        for (BeanDefinitionHolder holder : beanDefinitions) {
            AbstractBeanDefinition definition = (AbstractBeanDefinition) holder.getBeanDefinition();
            String beanClassName = definition.getBeanClassName();
            Class<?> beanClass = null;
            try {
                beanClass = Class.forName(beanClassName);
                Method registerConditionMethod = this.remoteChannelFactoryBeanClass.getMethod("registerCondition", Class.class);
                Object registerCondition = registerConditionMethod.invoke(null, beanClass);
                if (!((boolean) registerCondition)) {
                    continue;
                }
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            definition.getConstructorArgumentValues().addGenericArgumentValue(Objects.requireNonNull(beanClassName));
            definition.getPropertyValues().add("mapperInterface", beanClass);
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
            if (RemoteProxyEnhancer.class.getName().equals(interfaceName) && metadata.hasAnnotation(RemoteHandler.class.getName())) {
                return true;
            }
        }
        return false;
    }
}
