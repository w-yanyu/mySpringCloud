package org.remote.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Import(value = {RemoteScanRegistrar.class})
public @interface RemoteScan {
    RemoteChannelScan channelScan() default @RemoteChannelScan;

    RemoteEnhancerScan enhancerScan() default @RemoteEnhancerScan;

    RemoteHandlerScan handlerScan() default @RemoteHandlerScan;
}