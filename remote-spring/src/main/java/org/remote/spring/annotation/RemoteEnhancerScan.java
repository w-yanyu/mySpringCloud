package org.remote.spring.annotation;

import org.remote.spring.component.RemoteEnhancerBeanPostProcessor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RemoteEnhancerScans.class)
@Import(value = {RemoteEnhancerScanRegistrar.class, RemoteEnhancerBeanPostProcessor.class})
public @interface RemoteEnhancerScan {
    String[] basePackages() default {};
}
