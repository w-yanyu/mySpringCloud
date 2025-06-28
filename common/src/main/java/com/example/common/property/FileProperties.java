package com.example.common.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FileProperties
 * @Author yanyu
 * @Date 2024/5/11 10:20
 * @Version 1.0
 * @Description TODO
 **/
// @RefreshScope 作用于切面类，会导致切面两次执行，因此配置为配置类进行属性读取
@RefreshScope
@Data
@Configuration
public class FileProperties {

    @Value("${file.name:i am file name}")
    private String name;

    @Value("${file.extension: i am file extension}")
    private String extension;
}
