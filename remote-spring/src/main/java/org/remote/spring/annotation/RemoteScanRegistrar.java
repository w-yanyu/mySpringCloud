package org.remote.spring.annotation;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class RemoteScanRegistrar implements ImportBeanDefinitionRegistrar {

    private final RemoteEnhancerScanRegistrar enhancerScanRegistrar = new RemoteEnhancerScanRegistrar();
    private final RemoteHandlerScanRegistrar handlerScanRegistrar = new RemoteHandlerScanRegistrar();
    private final RemoteChannelScanRegistrar channelScanRegistrar = new RemoteChannelScanRegistrar();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(RemoteScan.class.getName()));
        registerBeanDefinitions(attributes, "enhancerScan", enhancerScanRegistrar, importingClassMetadata, registry);
        registerBeanDefinitions(attributes, "handlerScan", handlerScanRegistrar, importingClassMetadata, registry);
        registerBeanDefinitions(attributes, "channelScan", channelScanRegistrar, importingClassMetadata, registry);
    }

    void registerBeanDefinitions(AnnotationAttributes attributes, String attributeName, Registrar registrar, AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        if (attributes != null) {
            AnnotationAttributes attributesAnnotation = attributes.getAnnotation(attributeName);
            registrar.registerBeanDefinitions(importingClassMetadata, attributesAnnotation, registry, registrar.generateBaseBeanName(importingClassMetadata, 0));
        }
    }
}
