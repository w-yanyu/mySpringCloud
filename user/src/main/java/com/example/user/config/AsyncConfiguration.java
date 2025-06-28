package com.example.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName AsyncConfiguration
 * @Author yanyu
 * @Date 2024/7/29 14:52
 * @Version 1.0
 * @Description TODO
 **/
@Configuration
@EnableAsync
public class AsyncConfiguration {

    @Bean("myThreadPool")
    public Executor myThreadPool(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(10);
        // 最大线程数
        executor.setMaxPoolSize(20);
        // 缓冲队列大小
        executor.setQueueCapacity(500);
        // 允许线程空闲时间
        executor.setKeepAliveSeconds(60);
        // 在应用程序关闭时是否等待所有任务完成
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 线程池名前缀
        executor.setThreadNamePrefix("my-thread-");
        // 拒绝策略 线程池已达到最大线程数且队列也已满，丢弃无法执行的任务，不予处理且不通知提交者
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        // 初始化线程池
        executor.initialize();
        return executor;
    }
}
