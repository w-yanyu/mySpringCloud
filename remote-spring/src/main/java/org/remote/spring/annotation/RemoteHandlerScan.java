package org.remote.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RemoteHandlerScans.class)
@Import({RemoteHandlerScanRegistrar.class})
public @interface RemoteHandlerScan {
    String[] basePackages() default {};
}
