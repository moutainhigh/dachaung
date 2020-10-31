package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  @author: Sun Qingxin
 *  @Date: 2020/7/9 12:49
 *  @Description:
 */
@Data
@TableName("t_document")
public class Document {

    /**
     *  @Param: 主键id
     */
    @TableId(value = "document_id",type = IdType.AUTO)
    private Integer id;

    private String docName;

    private String docPeople;

    private Date docTime;

    private Integer projectId;

    private Integer eventId;


    /**
     *  @Param: 类别（巡检文档、事件文档、知识库、实施文档、考核文档）
     */
    private String status;

    private String filepath;

    /**
     *  @Param: 审核状态（未审核、审核通过、审核未通过）
     */
    private String approvalStatus;

    private String auditConclusion;

    /**
     *  @Param: 审核人ID
     */
    private Integer reviewerId;

    private Date reviewTime;


    /**
     *  @Param: 罚款
     */
    private BigDecimal fine;

    private String remark;
}
