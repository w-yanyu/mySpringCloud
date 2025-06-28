package com.example.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order")
public class Order extends CommEntity {

    @TableId(type = IdType.ASSIGN_ID)
    @Column(comment = "主键序列号",length = 64)
    private String id;

    @Column(comment = "用户主键序列号",length = 64)
    private String userId;

    @Column(comment = "产品名",length = 64)
    private String productName;

    @Column(comment = "交易金额", type = MySqlTypeConstant.DECIMAL, length = 12, decimalLength = 4)
    private BigDecimal amount;

    /**
     * 不映射数据库字段
     */
    @TableField(exist = false)
    private User user;

}