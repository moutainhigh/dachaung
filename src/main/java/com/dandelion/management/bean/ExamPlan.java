package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

/**
 *  @author: Sun Qingxin
 *  @Date: 2020/7/9 13:37
 *  @Description:
 */
@Data
@TableName("t_exam_plan")
public class ExamPlan {
    @TableId(value="exam_plan_id",type= IdType.AUTO)
    private Integer id;


    /**
     *  @Param:计划单号
     */
    private String planId;


    /**
     *  @Param:负责人ID
     */
    private Integer principalId;


    /**
     *  @Param:计划开始时间
     */
    private DateTimeLiteralExpression.DateTime planBeginTime;


    /**
     *  @Param:计划结束时间
     */
    private DateTimeLiteralExpression.DateTime planEndTime;


    /**
     *  @Param:制定时间
     */
    private DateTimeLiteralExpression.DateTime planMakeTime;


    /**
     *  @Param:计划名称
     */
    private String planName;


    /**
     *  @Param:运维项目ID
     */
    private Integer projectId;


    /**
     *  @Param:计划内容
     */
    private String planDetail;


    /**
     *  @Param:运维单位ID
     */
    private Integer companyId;


    /**
     *  @Param:投入人力
     */
    private Integer manpower;


    /**
     *  @Param:计划类型
     */
    private String planType;


    /**
     *  @Param:巡检次数
     */
    private Integer numberOfInspections;


    /**
     *  @Param:周期
     */
    private String cycle;

    private String remark;

}
