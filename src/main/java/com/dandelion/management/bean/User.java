package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * User entity
 *
 * @author hongjianYang
 * @date 2020/7/8
 */
@Data
@TableName("t_user")
public class User {
    /**
     * 主键(数字id) 自动生成
     */
    @TableId(value="user_id",type=IdType.AUTO)
    private Integer id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态(正常\注销)
     */
    private String status;
    /**
     * 姓名
     */
    private String name;
    /**
     * 所属单位ID
     */
    private Integer companyId;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 身份证
     */
    @TableField("idcard")
    private String idCard;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 备注
     */
    private String remark;
    /**
     * 权限组ID(使用,隔开)
     */
    private String authorityId;
    /**
     * 用户对应的角色集合
     */
    @TableField(exist = false)
    private Set<Role> roles;

    @TableField(exist = false)
    private AuthorityGroup group;

}
