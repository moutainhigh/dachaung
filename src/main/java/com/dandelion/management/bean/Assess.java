package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * Assess entity
 *
 * @author hongjianYang
 * @date 2020/7/8
 */
@Data
@TableName("t_assess")
public class Assess {
    /**
     * 主键 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer assessId;
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 事件id
     */
    private Integer eventId;
    /**
     * 评论
     */
    private String comment;
    /**
     * 评分等级1-5
     */
    private Integer rating;
    /**
     * 评价时间
     */
    private Date ratingTime;
    /**
     * 评价人ID
     */
    private Integer reporterId;
    /**
     * 评价单位ID
     */
    private Integer unitId;
}
