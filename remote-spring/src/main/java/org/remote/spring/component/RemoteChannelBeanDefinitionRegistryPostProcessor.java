package org.remote.spring.component;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;


@Component
public class RemoteChannelBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
//        RemoteChannelScan annotation = getAnnotationForScan();
//        if (annotation != null) {
//            String[] basePackages = annotation.basePackages();
//            for (String basePackage : basePackages) {
//                Reflections reflections = new Reflections(basePackage);
//                Set<Class<?>> remoteInterfaces = reflections.getTypesAnnotatedWith(RemoteChannel.class);
//                for (Class<?> remoteInterface : remoteInterfaces) {
//                    registerProxyBean(beanDefinitionRegistry, remoteInterface);
//                }
//            }
//        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

//    private RemoteChannelScan getAnnotationForScan() {
//        return RemoteApplication.class.getAnnotation(RemoteChannelScan.class);
//    }
//
//    private void registerProxyBean(BeanDefinitionRegistry beanDefinitionRegistry, Class<?> remoteInterface) {
//        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//        beanDefinition.setBeanClass(RemoteChannelProxyFactoryBean.class);
//        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(remoteInterface);
//        beanDefinitionRegistry.registerBeanDefinition(remoteInterface.getSimpleName(), beanDefinition);
//    }

}
