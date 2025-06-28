package com.example.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName CommEntity
 * @Author yanyu
 * @Date 2024/5/7 9:53
 * @Version 1.0
 * @Description TODO
 **/
@Data
public class CommEntity {

    @TableField(fill = FieldFill.INSERT)
    @Column(comment = "创建时间",  type = MySqlTypeConstant.DATETIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Column(comment = "创建人", length = 64)
    private String createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Column(comment = "修改时间",  type = MySqlTypeConstant.DATETIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @Column(comment = "修改人", length = 64)
    private String updateBy;
}
