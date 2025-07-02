package com.example.remote;

import com.example.remote.annotations.RemoteChannelScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName RomoteApplication
 * @Author yanyu
 * @Date 2024/5/8 20:55
 * @Version 1.0
 * @Description TODO
 **/
@SpringBootApplication
@RemoteChannelScan(basePackages = {"com.example.remote.service"})
public class RemoteApplication {
    public static void main(String[] args) {
        SpringApplication.run(RemoteApplication.class, args);
    }
}
