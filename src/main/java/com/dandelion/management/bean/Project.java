package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;


/**
 *  @author: Sun Qingxin
 *  @Date: 2020/7/9 15:21
 *  @Description:
 */
@Data
@TableName("t_project")
public class Project {

    /**
     * 主键(数字id) 自动生成   是项目编号么？
     */
    @TableId(value="project_id",type= IdType.AUTO)
    private Integer projectId;

    /**
     *  @Param:项目名
     */
    private String projectName;

    /**
     *  @Param:金额
     */
    private BigDecimal amount;

    /**
     *  @Param:负责人
     */
    private String principal;

    /**
     *  @Param:联系方式
     */
    private String principalPhone;

    /**
     *  @Param:单位id 外键
     */
    private Integer companyId;

    /**
     *  @Param:合同id
     */
    private Integer contractId;

    /**
     *  @Param:隶属行业
     */
    private String companyType;

    /**
     *  @Param:终验日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finalInspectionDate;

    /**
     *  @Param:质保日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date warrantyDate;

    /**
     *  @Param:项目简介
     */
    @TableField(value = "project_description")
    private String projectDes;

    /**
     *  @Param:管理单位id 外键
     */
    private Integer managementCompanyId;

    /**
     *  @Param:项目专管员
     */
    private String management;

    /**
     *  @Param:备注   (因缺失地址字段 暂占用为地址):Yang
     */
    private String remarks;
}
