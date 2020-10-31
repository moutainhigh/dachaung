package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import javafx.scene.chart.ValueAxis;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author: Sun Qingxin
 * @Date: 2020/7/9 13:08
 * @Description:
 */
@Data
@TableName(value = "t_event")
public class Event {
    @TableId(value = "event_id", type = IdType.AUTO)
    private Integer id;

    private Integer projectId;

    /**
     * 报单人ID （人员表中应包含电话及单位）
     */
    private Integer reporterId;

    /**
     * 处理人ID （人员表中应包含电话及单位）
     */
    private Integer processorId;

    /**
     * 承建方负责人ID （人员表中应包含电话及单位）
     */
    private Integer contractorId;

    /**
     * 运维方负责人ID （人员表中应包含电话及单位）
     */
    @TableField("operation_maintenance_id")
    private Integer operationId;

    /**
     * 报障时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reportTime;

    /**
     * 地址
     */
    private String address;

    /**
     * 报单描述
     */
    @TableField("defect_description")
    private String defectDes;

    /**
     * 处理描述
     */
    @TableField("processing_description")
    private String processingDes;

    /**
     * 报单方式
     */
    private String reportingMethod;


    /**
     * 报单分类
     */
    private String faultClassification;

    /**
     * 故障紧急度
     */
    private String faultUrgency;

    private double longitude;

    private double latitude;

    private String faultArea;

    /**
     * 审核意见
     */
    private String auditOpinion;

    /**
     * 报障文件ID
     */
    private Integer obstacleReportId;

    /**
     * 审核状态
     */
    private String approvalStatus;

    /**
     * 派单单位
     */
    private String dispatchUnit;

    /**
     * 审核人
     */
    private String reviewer;

    /**
     * 现场描述
     */
    private String sceneDescription;

    /**
     * 事件分析
     */
    private String approach;

    /**
     * 运维方建议
     */
    private String maintenanceRecommendations;

    /**
     * 现场文件id
     */
    private Integer onSiteDocuments;

    /**
     * 评价
     */
    private String evaluation;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 派单时间
     */
    private Timestamp timestamp;

    /**
     * 处理时间
     */
    private DateTimeLiteralExpression.DateTime processingTime;

    /**
     * 提醒标志
     */
    private String remind;

    private String remark;

    /**
     * 事件状态（申报、派单、结案、评价)
     */
    private String status;
}

