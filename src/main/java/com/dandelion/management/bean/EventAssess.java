package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 评论视图实体类
 *
 * @author hongjian Yang
 * @date 2020/8/14
 */
@Data
@TableName("v_assess_event")
public class EventAssess {
    /**
     * 事件id
     */
    private Integer eventId;
    /**
     * 事件描述
     */
    private String defectDescription;
    /**
     * 评论
     */
    private String comment;
    /**
     * 评分
     */
    private Integer rating;
    /**
     * 评价时间
     */
    private Date ratingTime;
    /**
     * 评价人公司
     */
    private String commentatorCompany;
    /**
     * 评价人姓名
     */
    private String commentatorName;
    /**
     * 评价人手机
     */
    private String commentatorPhone;
    /**
     * 运维方公司
     */
    private String maintenanceCompany;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 评价id
     */
    @TableId(value="assess_id",type= IdType.AUTO)
    private Integer assessId;
    /**
     * 报单分类
     */
    private String faultClassification;
    /**
     * 故障紧急度
     */
    private String faultUrgency;
    /**
     * 报单方式
     */
    private String reportingMethod;
}
