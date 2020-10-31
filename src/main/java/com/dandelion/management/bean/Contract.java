package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Contract entity
 *
 * @author hongjianYang
 * @date 2020/7/8
 */
@Data
@TableName("t_contract")
public class Contract {
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 主键 自增
     */
    @TableId(value = "contract_id",type = IdType.AUTO)
    private Integer contractId;
    /**
     * 合同编号
     */
    private String contractNo;
    /**
     * 合同名
     */
    private String contractName;
    /**
     * 甲方名称
     */
    private String partyA;
    /**
     * 乙方名称
     */
    private String partyB;
    /**
     * 监理方
     */
    private String supervisor;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 签订日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date signingDate;
    /**
     * 开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    /**
     * 截止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadline;
    /**
     * 付款条件
     */
    private String paymentTerms;
    /**
     * 合同简介
     */
    private String contractIntroduction;
    /**
     * 合同类型
     */
    private String typeContract;
    /**
     * 合同文件
     */
    private Integer fileId;
    /**
     * 备注
     */
    private String remarks;
}
