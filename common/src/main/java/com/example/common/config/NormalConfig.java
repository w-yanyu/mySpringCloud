package com.example.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName NormalConfig
 * @Author yanyu
 * @Date 2024/4/28 9:29
 * @Version 1.0
 * @Description TODO
 **/
@Configuration
@EnableAspectJAutoProxy
@MapperScan(basePackages = {"com.example.common.mapper"})
public class NormalConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
