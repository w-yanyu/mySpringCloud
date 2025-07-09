package org.remote.config;


import org.remote.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Config instance = new Config();
    private final Properties properties = new Properties();

    {
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Config() {
    }

    public static boolean containsKey(Object key) {
        return instance.properties.containsKey(key);
    }

    public static String get(String key) {
        String property = instance.properties.getProperty(key);
        if (StringUtils.isEmpty(property)) {
            property = System.getProperty(key);
        }
        return property;
    }

    public static String get(String key, String defaultValue) {
        String property = get(key);
        if (StringUtils.isEmpty(property)) {
            property = defaultValue;
        }
        return property;
    }
}
