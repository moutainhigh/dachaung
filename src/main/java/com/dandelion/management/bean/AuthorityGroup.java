package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * AuthorityGroup entity
 *
 * @author hongjianYang
 * @date 2020/7/8
 */
@Data
@TableName("t_authority_group")
public class AuthorityGroup {
    /**
     * 主键 自增
     */
    @TableId(value = "authority_group_id",type = IdType.AUTO)
    private Integer authorityGroupId;
    /**
     * 权限组名称
     */
    private String authorityGroupName;
    /**
     * 权限组id
     */
    private String authorityId;

    /**
     * 存储所有权限的详细配置
     */
    @TableField(exist = false)
    private List<Authority> authorities;

    /**
     * authority_group_english_name
     */
    @TableField("authority_group_english_name")
    private String englishName;
}
