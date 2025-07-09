package org.remote.spring.annotation;


import org.remote.spring.channel.RemoteChannelScannerConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoteChannelScanRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(RemoteChannelScan.class.getName()));
        if (mapperScanAttrs != null) {
            this.registerBeanDefinitions(importingClassMetadata, mapperScanAttrs, registry, generateBaseBeanName(importingClassMetadata, 0));
        }
    }

    void registerBeanDefinitions(AnnotationMetadata annoMeta, AnnotationAttributes annoAttrs, BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RemoteChannelScannerConfigurer.class);
        List<String> basePackages = Arrays.stream(annoAttrs.getStringArray("basePackages")).filter(StringUtils::hasText).collect(Collectors.toList());
        if (basePackages.isEmpty()) {
            basePackages.add(getDefaultBasePackage(annoMeta));
        }
        builder.addPropertyValue("basePackages", basePackages);
        registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
    }

    private static String generateBaseBeanName(AnnotationMetadata importingClassMetadata, int index) {
        return importingClassMetadata.getClassName() + "#" + RemoteChannelScanRegistrar.class.getSimpleName() + "#" + index;
    }

    private static String getDefaultBasePackage(AnnotationMetadata importingClassMetadata) {
        return ClassUtils.getPackageName(importingClassMetadata.getClassName());
    }

    static class RepeatingRegistrar extends RemoteChannelScanRegistrar {
        RepeatingRegistrar() {
        }

        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            AnnotationAttributes mapperScansAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(RemoteChannelScans.class.getName()));
            if (mapperScansAttrs != null) {
                AnnotationAttributes[] annotations = mapperScansAttrs.getAnnotationArray("value");

                for (int i = 0; i < annotations.length; ++i) {
                    this.registerBeanDefinitions(importingClassMetadata, annotations[i], registry, RemoteChannelScanRegistrar.generateBaseBeanName(importingClassMetadata, i));
                }
            }

        }
    }
}
