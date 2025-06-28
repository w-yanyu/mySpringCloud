package com.example.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *  @TableId(type = IdType.ASSIGN_ID) 当没有手动设置主键，即实体类中的主键属性为空时，才会自动填充，使用雪花算法
 *  配合@KeySequence(value = "idGenerator")，使用自定义序列生成
 *  1、此方法生成序列，依赖数据库，需实现IKeyGenerator接口
 * /1、此方法生成序列，依赖自定义算法，需实现IdentifierGenerator
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
@KeySequence(value = "idGenerator")
public class User extends CommEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Column(comment = "主键序列号",length = 64)
    private String id;

    @Column(comment = "用户名",length = 64)
    private String username;

    @Column(comment = "邮箱",length = 64)
    private String email;

}