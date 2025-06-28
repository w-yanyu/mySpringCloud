package com.example.task.timer;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @ClassName TaskSubmitJob
 * @Author yanyu
 * @Date 2025/2/5 17:43
 * @Version 1.0
 * @Description TODO
 **/
@Slf4j
public class TaskSubmitJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("任务执行。。。");
    }
}
