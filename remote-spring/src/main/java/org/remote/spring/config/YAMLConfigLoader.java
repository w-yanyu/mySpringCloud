package org.remote.spring.config;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class YAMLConfigLoader {
    private static Map<String, Object> loadConfigFile(String yamlFilePath) throws IOException {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = YAMLConfigLoader.class.getClassLoader().getResourceAsStream(yamlFilePath)) {
            return yaml.load(inputStream);
        }
    }

    private static Map<String, Object> mergeConfigs(Map<String, Object> baseConfig, Map<String, Object> newConfig) {
        if (newConfig != null) {
            for (Map.Entry<String, Object> entry : newConfig.entrySet()) {
                String key = entry.getKey();
                Object newValue = entry.getValue();

                if (!baseConfig.containsKey(key)) {
                    baseConfig.put(key, newValue);
                } else {
                    Object oldValue = baseConfig.get(key);
                    if (oldValue instanceof Map && newValue instanceof Map) {
                        baseConfig.put(key, mergeConfigs((Map<String, Object>) oldValue, (Map<String, Object>) newValue));
                    } else {
                        baseConfig.put(key, newValue);
                    }
                }
            }
        }
        return baseConfig;
    }

    public static Map<String, Object> loadAllConfigsWithOverride(String[] yamlFilePaths) throws IOException {
        Map<String, Object> mergedConfig = new HashMap<>();

        for (String yamlFilePath : yamlFilePaths) {
            Map<String, Object> config = loadConfigFile(yamlFilePath);
            mergedConfig = mergeConfigs(mergedConfig, config);
        }

        return mergedConfig;
    }

    public static void main(String[] args) throws IOException {
        // 配置文件路径
        String[] paths = {"application.yml", "application1.yml",};

        // 合并配置并输出最终结果
        Map<String, Object> finalConfig = loadAllConfigsWithOverride(paths);
        System.out.println(finalConfig);
    }
}