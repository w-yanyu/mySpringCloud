package org.remote.spring.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RemoteEnhancerScans.class)
public @interface RemoteEnhancerScan {
    String[] basePackages() default {};
}
