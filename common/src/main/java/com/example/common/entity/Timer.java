package com.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName Timer
 * @Author yanyu
 * @Date 2025/1/30 18:06
 * @Version 1.0
 * @Description TODO
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_timer")
public class Timer extends CommEntity{

    /**
     * mybatisplus 不支持多字段联合主键
     */
    @TableId(value = "tran_id", type = IdType.INPUT)
    @Column(comment = "交易id",length = 64)
    private String tranId;

    @Column(comment = "交易组",length = 64)
    private String tranGroup;

    @Column(comment = "交易码",length = 64)
    private String tranCode;

    @Column(comment = "执行类名",length = 128)
    private String exeClassName;

    @Column(comment = "交易描述",length = 64)
    private String tranDescribe;

    @Column(comment = "秒",length = 8)
    private String second;

    @Column(comment = "分钟",length = 8)
    private String minute;

    @Column(comment = "小时",length = 8)
    private String hour;

    @Column(comment = "日",length = 8)
    private String dayOfMonth;

    @Column(comment = "月",length = 8)
    private String month;

    @Column(comment = "周",length = 8)
    private String dayOfWeek;

    @Column(comment = "状态",length = 16)
    private String status;

    @Column(comment = "执行任务id", length = 64)
    private String taskId;

    /**
     * 组装 Cron 表达式
     * @return
     */
    public String getCronExpression() {
        return String.format("%s %s %s %s %s %s", second, minute, hour, dayOfMonth, month, ("*".equals(dayOfWeek) ? "?" : dayOfWeek));
    }

}
