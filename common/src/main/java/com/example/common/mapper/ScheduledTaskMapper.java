package com.example.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.entity.Timer;
import org.springframework.stereotype.Repository;

/**
 * @Author yanyu
 * @Date 2025/2/1 19:59
 * @Version 1.0
 * @Description TODO
 **/
@Repository
public interface ScheduledTaskMapper extends BaseMapper<Timer> {
}
