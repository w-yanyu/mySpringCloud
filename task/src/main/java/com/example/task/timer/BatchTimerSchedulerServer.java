package com.example.task.timer;

import com.example.common.entity.Timer;
import com.example.common.utils.ReflectionUtil;
import com.example.task.service.TaskSubmitJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @ClassName BatchTimerSchedulerServer
 * @Author yanyu
 * @Date 2025/2/5 16:02
 * @Version 1.0
 * @Description TODO
 **/
@Component
@Slf4j
public class BatchTimerSchedulerServer {

    private static final String TIMER_NAME_PREFIX = "timer-tran-busi-";
    private static final String TIMER_GROUP = "timer-tran-group";
    private static final String TRIGGER_NAME_PREFIX = "timer-tran-busi-trigger-";
    private static final String TRIGGER_GROUP = "timer-tran-trigger-group";
    private volatile boolean isStarted = false;

    @Autowired
    private BatchTimerSchedulerPollThread thread;
    private Scheduler scheduler;

    public void startup() {
        if (!isStarted) {
            try {
                this.scheduler = StdSchedulerFactory.getDefaultScheduler();
                this.scheduler.start();
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
            this.thread.startup();
            this.isStarted = true;
        }
    }

    public void shutdown(boolean immediate) {
        if (this.isStarted) {
            this.isStarted = false;
            if (this.thread != null) {
                this.thread.shutdown();
            }

            if (this.scheduler != null) {
                try {
                    this.scheduler.shutdown();
                } catch (SchedulerException e) {
                    log.error("quartz调度器停止发生异常", e);
                }
            }
        }
    }

    public void addJob(Timer timer) {
        try {
            JobDetail jobDetail = JobBuilder.newJob().withIdentity(TIMER_NAME_PREFIX + timer.getTranGroup() + timer.getTranId(), TIMER_GROUP).ofType(this.getJobType(timer)).withDescription(timer.getTranDescribe()).build();
            jobDetail.getJobDataMap().put("task", timer);
            String cronExpression = timer.getCronExpression();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(TRIGGER_NAME_PREFIX + timer.getTranGroup() + timer.getTranId(), TRIGGER_GROUP).forJob(jobDetail).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
            if (this.scheduler.checkExists(jobDetail.getKey())) {
                log.info("任务{}已存在，不进行处理", timer.getTranGroup() + timer.getTranId());
            } else {
                this.scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (SchedulerException e) {
            log.error("调度器添加任务{}失败！", timer.getTranGroup() + timer.getTranId(), e);
        }
    }

    public void deleteAllJob() {
        try {
            this.scheduler.clear();
        } catch (SchedulerException e) {
            log.error("释放所有的定时任务失败", e);
        }
    }

    public void deleteJob(Timer timer) {
        JobKey jobKey = JobKey.jobKey(TIMER_NAME_PREFIX + timer.getTranGroup() + timer.getTranId(), TIMER_GROUP);

        try {
            this.scheduler.deleteJob(jobKey);
            log.info("删除任务{}成功", timer.getTranGroup() + timer.getTranId());
        } catch (SchedulerException e) {
            log.error("删除任务{}失败", timer.getTranGroup() + timer.getTranId(), e);
        }
    }

    private Class<? extends Job> getJobType(Timer task) {
        if (!StringUtils.hasLength(task.getExeClassName())) {
            return TaskSubmitJob.class;
        } else {
            Class<?> clazz = ReflectionUtil.classForName(task.getExeClassName());
            if (Job.class.isAssignableFrom(clazz)) {
                return (Class<? extends Job>) clazz;
            } else {
                throw new RuntimeException("执行类初始化异常！");
            }
        }
    }
}
