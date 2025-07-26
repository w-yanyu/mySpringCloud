package org.remote.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RemoteEnhancerScans.class)
@Import(value = {RemoteEnhancerScanRegistrar.class})
public @interface RemoteEnhancerScan {
    String[] basePackages() default {};
}
