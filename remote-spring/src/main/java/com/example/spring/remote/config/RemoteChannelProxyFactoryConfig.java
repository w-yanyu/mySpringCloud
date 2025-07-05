package com.example.spring.remote.config;

import com.example.remote.config.RemoteChannelProxyFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoteChannelProxyFactoryConfig {

    public RemoteChannelProxyFactory remoteChannelProxyFactory(){
        return new RemoteChannelProxyFactory();
    }
}
