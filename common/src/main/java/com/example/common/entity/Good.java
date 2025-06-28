package com.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName Good
 * @Author yanyu
 * @Date 2024/5/12 20:37
 * @Version 1.0
 * @Description TODO
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_good")
public class Good extends CommEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Column(comment = "主键序列号",length = 64)
    private String id;

    @Column(comment = "商品名",length = 64)
    private String goodName;

    /**
     * 整数8位，小数4位
     */
    @Column(comment = "单价", type = MySqlTypeConstant.DECIMAL, length = 12, decimalLength = 4)
    private BigDecimal unitPrice;
}
