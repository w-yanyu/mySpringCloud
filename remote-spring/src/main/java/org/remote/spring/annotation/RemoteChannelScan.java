package org.remote.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RemoteChannelScans.class)
@Import(RemoteChannelScanRegistrar.class)
public @interface RemoteChannelScan {
    String[] basePackages() default {};
}
