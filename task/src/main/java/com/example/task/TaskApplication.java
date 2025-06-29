package com.example.task;

import com.example.common.aspect.WebLogAspectHandler;
import com.example.common.config.MybatisPlusConfig;
import com.example.common.config.NormalConfig;
import com.example.common.property.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @ClassName TaskApplication
 * @Author yanyu
 * @Date 2025/2/1 14:19
 * @Version 1.0
 * @Description TODO
 **/
@SpringBootApplication
@MapperScan(basePackages = {"com.example.task.mapper"})
@Import({WebLogAspectHandler.class, MybatisPlusConfig.class, NormalConfig.class, FileProperties.class})
public class TaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}
