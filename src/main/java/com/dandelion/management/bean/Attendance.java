package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * Attendance entity
 *
 * @author hongjianYang
 * @date 2020/7/8
 */
@Data
@TableName("t_attendance")
public class Attendance {
    /***
     * 主键 自增
     */
    @TableId(value = "attendance_id",type = IdType.AUTO)
    private Integer attendanceId;
    /***
     * 项目
     */
    private Integer projectId;
    /***
     * 签到时间
     */
    private Date time;
    /***
     * 用户名
     */
    private Integer userId;
    /***
     * 经度
     */
    private Double longitude;
    /***
     * 纬度
     */
    private Double latitude;
    /***
     * 备注
     */
    private String remark;
}
