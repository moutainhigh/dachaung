package com.dandelion.management.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Alarm entity
 *
 * @author hongjianYang
 * @date 2020/7/8
 */
@TableName("t_alarm")
@Data
public class Alarm {
    /**
     * 主键 自增
     */
    @TableId(value="alarm_id",type = IdType.AUTO)
    private Integer id;
    /**
     * 延误时间
     */
    private Date delayTime;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 时间状态 已整改/未整改
     */
    private String eventStatus;
    /**
     * 罚款金额
     */
    private BigDecimal fine;
    /**
     * 处理人ID
     */
    private Integer userId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 事件ID
     */
    private Integer eventId;
    /**
     * 预警内容 事件/巡检
     */
    private String alarmInfo;
}
