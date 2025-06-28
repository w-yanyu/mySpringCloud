package com.example.order;

import com.example.common.aspect.WebLogAspectHandler;
import com.example.common.config.DefaultFeignConfigration;
import com.example.common.config.MybatisPlusConfig;
import com.example.common.config.NormalConfig;
import com.example.common.property.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.order.mapper"})
@EnableAspectJAutoProxy
@EnableDiscoveryClient
@Import({WebLogAspectHandler.class, MybatisPlusConfig.class, NormalConfig.class, FileProperties.class})
@EnableFeignClients(basePackages = {"com.example.common.client"},defaultConfiguration = {DefaultFeignConfigration.class})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
