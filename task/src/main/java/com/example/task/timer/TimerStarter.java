package com.example.task.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @ClassName TimerStarter
 * @Author yanyu
 * @Date 2025/2/7 14:11
 * @Version 1.0
 * @Description TODO
 **/
@Component
public class TimerStarter {

    @Autowired
    private BatchTimerSchedulerServer batchTimerSchedulerServer;

    @PostConstruct
    public void startUp(){
        this.batchTimerSchedulerServer.startup();
    }
    @PreDestroy
    public void shutdown(){
        try {
            this.batchTimerSchedulerServer.shutdown(false);
        } catch (Exception e) {
            this.batchTimerSchedulerServer.shutdown(true);
            throw e;
        }
    }
}
