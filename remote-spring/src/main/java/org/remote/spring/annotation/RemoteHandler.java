package org.remote.spring.annotation;

import org.remote.manager.impl.DefaultRemoteEnhancerManager;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RemoteHandler {
    String[] channels() default {DefaultRemoteEnhancerManager.WILDCARD};
}
