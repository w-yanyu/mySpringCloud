package com.example.common.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName DefaultFeignConfigration
 * @Author yanyu
 * @Date 2024/5/9 23:07
 * @Version 1.0
 * @Description TODO
 **/
public class DefaultFeignConfigration {

    /**
     * feign 日志级别配置
     * @return
     */
    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.FULL;
    }

}
