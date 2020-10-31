package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;


/**
 *  @author: Sun Qingxin
 *  @Date: 2020/7/9 14:54
 *  @Description:
 */
@Data
@TableName(value = "t_exam_record")
public class ExamRecord {

    @TableId(value="record_id",type= IdType.AUTO)
    private Integer id;

    /**
     *  @Param:巡检单号
     */
    private String recordNumber;

    /**
     *  @Param:计划ID
     */
    private Integer planId;

    /**
     *  @Param:巡检时间
     */
    private Date inspectionTime;

    /**
     *  @Param:提交时间
     */
    private Date submitTime;

    /**
     *  @Param:时间戳
     */
    private Timestamp timestamp;

    /**
     *  @Param:状态
     */
    private String recordStatus;

    /**
     *  @Param:是否延期
     */
    private String delayFlag;

    /**
     *  @Param:评价
     */
    private String evaluation;

    /**
     *  @Param:备注
     */
    private String remark;

    /**
     *  @Param:是否正常
     */
    private String isNormal;

    /**
     *  @Param:巡检方法
     */
    private String inspectionMethod;

    /**
     *  @Param:经度
     */
    private double longitude;

    /**
     *  @Param:纬度
     */
    private double latitude;

    /**
     *  @Param:巡检人
     */
    private Integer executor;

    /**
     *  @Param:附件图片路径（多个图片使用分号隔开）
     */
    private String imagesUrl;

}
