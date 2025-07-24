package org.remote.spring.test;

import org.remote.spring.annotation.RemoteChannelScan;
import org.remote.spring.annotation.RemoteEnhancerScan;
import org.remote.spring.annotation.RemoteHandlerScan;
import org.remote.spring.annotation.RemoteScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@RemoteScan(
        channelScan = @RemoteChannelScan(basePackages = {"org.remote.spring.test"}),
        enhancerScan = @RemoteEnhancerScan(basePackages = {"org.remote.spring.test"}),
        handlerScan = @RemoteHandlerScan(basePackages = {"org.remote.spring.test"})
)
//@RemoteHandlerScan(basePackages = {"org.remote.spring.test"})
@ComponentScan(value = {"org.remote.spring"})
public class Test {
    public static void main(String[] args) {
        SpringApplication.run(Test.class, args);
    }
}
