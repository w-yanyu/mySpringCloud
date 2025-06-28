package com.example.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName TaskApplication
 * @Author yanyu
 * @Date 2025/2/1 14:19
 * @Version 1.0
 * @Description TODO
 **/
@SpringBootApplication
//@Import({NormalConfig.class})
@ComponentScan({"com.example.common.mapper","com.example.task"})
@MapperScan(basePackages = {"com.example.common.mapper"})
public class TaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}
