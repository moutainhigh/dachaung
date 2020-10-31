package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 基于事件的数据视图 目前仅作为查询依据(需在非空数据全部填写完整后 才可通过视图进行查询)
 * todo layui分页失效
 * @author hongjianYang
 * @date 2020/7/27
 */
@Data
@TableName("v_all_event_msg")
public class EventInfo {
    /**
     * 事件id
     */
    @TableId(value="event_id",type= IdType.AUTO)
    public Integer eventId;
    /**
     * 项目id
     */
    public String projectId;
    /**
     * 项目名称
     */
    public String projectName;
    /**
     * 报单人姓名
     */
    public String reporterName;
    /**
     * 报单人联系方式
     */
    public String reporterPhone;
    /**
     * 报单人公司
     */
    public String reporterCompany;
    /**
     * 运维方负责人
     */
    public String maintenanceName;
    /**
     * 运维方负责人联系方式
     */
    public String maintenancePhone;
    /**
     * 运维方负责人所在公司
     */
    public String maintenanceCompany;
    /**
     * 施工方负责人
     */
    public String contractorName;
    /**
     * 施工方负责人联系方式
     */
    public String contractorPhone;
    /**
     * 施工方负责人所在公司
     */
    public String contractorCompany;
    /**
     * 处理人姓名
     */
    public String processorName;
    /**
     * 处理人联系方式
     */
    public String processorPhone;
    /**
     * 处理人所在公司
     */
    public String processorCompany;

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
