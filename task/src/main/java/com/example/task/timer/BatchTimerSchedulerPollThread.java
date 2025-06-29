package com.example.task.timer;

import com.example.common.entity.Timer;
import com.example.common.enumeration.TranEnum;
import com.example.task.mapper.TimerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Iterator;
import java.util.List;

/**
 * @ClassName BatchTimerSchedulerPollThread
 * @Author yanyu
 * @Date 2025/2/5 15:52
 * @Version 1.0
 * @Description TODO
 **/
@Slf4j
@Component
public class BatchTimerSchedulerPollThread extends AbstractPollThread {

    @Autowired
    @Lazy
    private BatchTimerSchedulerServer timerSchedulerServer;
    private boolean master = true;

    @Autowired
    private TimerMapper timerMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void process() {
        this.transactionTemplate.execute(status -> {
            try {
                List<com.example.common.entity.Timer> timers = timerMapper.selectList(null);
                if (!this.master) {
                    this.process(timers);
                } else {
                    this.process(timers);
                }
                return true;
            } catch (Exception e) {
                status.setRollbackOnly(); // 发生异常时回滚
                return false;
            }
        });
    }

    private void process(List<Timer> timers) {
        if (timers != null && timers.size() != 0) {
            Iterator<Timer> iterator = timers.iterator();
            while (iterator.hasNext()) {
                Timer timer = iterator.next();
                try {
                    switch (TranEnum.TimerStatus.valueOf(timer.getStatus())) {
                        case START:
                            this.timerSchedulerServer.addJob(timer);
                            timer.setStatus(TranEnum.TimerStatus.SCHEDULING.name());
                            this.transactionTemplate.execute(status -> {
                                timerMapper.updateById(timer);
                                return true;
                            });
                            log.info("状态{}的任务{}已经添加到Quartz!", timer.getStatus(), timer.getTranGroup() + "-" + timer.getTaskId() + ":" + timer.getTranDescribe());
                            break;
                        case SCHEDULING:
                            this.timerSchedulerServer.addJob(timer);
                            log.info("状态{}的任务{}已经添加到Quartz!", timer.getStatus(), timer.getTranGroup() + "-" + timer.getTaskId() + ":" + timer.getTranDescribe());
                            break;
                        case STOPPING:
                            this.timerSchedulerServer.deleteJob(timer);
                            timer.setStatus(TranEnum.TimerStatus.STOPPED.name());
                            this.transactionTemplate.execute(status -> {
                                timerMapper.updateById(timer);
                                return true;
                            });
                            break;
                        case STOPPED:
                            break;
                        case DELETE:
                            this.timerSchedulerServer.deleteJob(timer);
                            this.transactionTemplate.execute(status -> {
                                timerMapper.deleteById(timer);
                                return true;
                            });
                            break;
                    }
                } catch (Exception e) {
                    log.error("异常：", e);
                }
            }
        }
    }
}
