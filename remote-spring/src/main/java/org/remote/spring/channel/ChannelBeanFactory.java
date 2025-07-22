package org.remote.spring.channel;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ChannelBeanFactory {
    private static final ChannelBeanFactory instance = new ChannelBeanFactory();
    private final Set<Class<?>> channelClassSet = new CopyOnWriteArraySet<>();

    private ChannelBeanFactory() {

    }

    public static ChannelBeanFactory getInstance() {
        return instance;
    }

    public Set<Class<?>> getChannelClassSet() {
        return channelClassSet;
    }
}
