package org.remote.annotation;


import org.remote.manager.impl.DefaultRemoteEnhancerManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RemoteEnhancer {
    String value() default DefaultRemoteEnhancerManager.WILDCARD;

    String[] methods() default {DefaultRemoteEnhancerManager.WILDCARD};

    int order() default Integer.MAX_VALUE / 2;
}