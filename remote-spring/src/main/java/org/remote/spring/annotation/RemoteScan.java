package org.remote.spring.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RemoteScan {
    RemoteChannelScan channelScan() default @RemoteChannelScan;

    RemoteEnhancerScan enhancerScan() default @RemoteEnhancerScan;
}