package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Company entity
 *
 * @author hongjian Yang
 * @date 2020/7/8
 */
@Data
@TableName("t_company")
public class Company {
    /**
     * 主键 自增
     */
    @TableId(value = "company_id",type = IdType.AUTO)
    private Integer companyId;
    /**
     * 法定代表人
     */
    private String corporation;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 信用代码
     */
    private String creditCode;
    /**
     * 隶属地
     */
    private String subordinateGrid;
    /**
     * 联系电话
     */
    private String contactNumber;
    /**
     * 公司类型
     */
    private String unitType;
    /**
     * 工作地址
     */
    private String workAddress;
    /**
     * 隶属行业
     */
    private String industry;
    /**
     * 单位简介
     */
    private String introduction;
    /**
     * 备注
     */
    private String remarks;
}
