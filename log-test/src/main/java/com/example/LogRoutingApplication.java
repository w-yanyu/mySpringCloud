package com.example;

import com.example.common.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({WebConfig.class})
public class LogRoutingApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogRoutingApplication.class, args);
    }
}