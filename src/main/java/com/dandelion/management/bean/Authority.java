package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Authority entity
 *
 * @author hongjianYang
 * @date 2020/7/8
 */
@Data
@TableName("t_authority")
public class Authority {
    /**
     * 主键 自增
     */
    @TableId(value = "authority_id",type = IdType.AUTO)
    private Integer authorityId;
    /**
     * 权限名
     */
    private String authorityName;
    /**
     * 权限对应URL
     */
    private String authorityUrl;
    /**
     * 权限描述
     */
    private String authorityDes;
    /**
     * 备注
     */
    private String remark;
}
