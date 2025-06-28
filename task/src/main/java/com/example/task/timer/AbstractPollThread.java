package com.example.task.timer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.TimerTask;

/**
 * @ClassName AbstractPollThread
 * @Author yanyu
 * @Date 2025/2/7 13:23
 * @Version 1.0
 * @Description TODO
 **/

@Component
@Slf4j
public abstract class AbstractPollThread {

    private final static String TIMER_THREAD_NAME_PREFIX = "timer-";
    private long delay = 60L;
    private long timeInterval = 60L;

    @Autowired
    private TransactionTemplate transactionTemplate;

    private java.util.Timer timer;

    @Value("${batch-timer-scheduler-pollThread.name}")
    private String threadName;

    private boolean isTimerStart = false;
    private boolean isOnProcessing = false;

    public abstract void process();

    public void startup() {
        this.startup(false);
    }

    public void startup(boolean isDeamon) {
        this.timer = new java.util.Timer(TIMER_THREAD_NAME_PREFIX + this.threadName, isDeamon);
        try {
            this.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    AbstractPollThread.this.transactionTemplate.execute(status -> {
                        try {
                            AbstractPollThread.this.isOnProcessing = true;
                            AbstractPollThread.this.process();
                            AbstractPollThread.this.isOnProcessing = false;
                            return true;
                        } catch (Exception e) {
                            status.setRollbackOnly(); // 发生异常时回滚
                            return false;
                        }
                    });
                }
            }, this.delay * 1000L, this.timeInterval * 1000L);
            this.isTimerStart = true;
        } catch (Exception e) {
            this.isTimerStart = false;
            log.error("轮训线程设置定时器失败", e);
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        try {
            if (this.timer != null) {
                this.timer.cancel();
            }
            this.isTimerStart = false;
        } catch (Exception e) {
            this.isTimerStart = false;
            throw new RuntimeException("关闭轮询线程中的定时器失败", e);
        }
    }
}
