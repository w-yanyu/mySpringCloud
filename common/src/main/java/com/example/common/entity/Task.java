package com.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName Task
 * @Author yanyu
 * @Date 2025/1/30 18:06
 * @Version 1.0
 * @Description TODO
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_task")
public class Task extends CommEntity{

    /**
     * mybatisplus 不支持多字段联合主键
     */
    @TableId(value = "tran_id", type = IdType.INPUT)
    @Column(comment = "交易id",length = 64)
    private String tranId;
}
