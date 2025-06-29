package com.example.remote.dispatcher;

import com.example.remote.annotations.RemoteChannel;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HandlerRegistry
 * @Author yanyu
 * @Date 2025/5/17 17:04
 * @Version 1.0
 * @Description 处理器注册器
 **/
@Component
public class HandlerRegistry implements ApplicationContextAware {

    private ApplicationContext context;

    private final Map<String, Method> handlerMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @PostConstruct
    public void init() {
        Map<String, Object> beans = context.getBeansWithAnnotation(Component.class);
        for (Object bean : beans.values()) {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                RemoteChannel channel = method.getAnnotation(RemoteChannel.class);
                if (channel != null) {
                    handlerMap.put(channel.systemId() + method.getName(), method);
                }
            }
        }
    }

    public Object dispatcher(String system, String endpoint, Map<String, Object> payload) throws Exception {
        String key = system + ":" + endpoint;
        Method method = handlerMap.get(key);
        if (method == null) throw new RuntimeException("No handler for " + key);
        Object bean = context.getBean(method.getDeclaringClass());
        return method.invoke(bean, payload);
    }
}
