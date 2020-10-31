package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 *  @author: Sun Qingxin
 *  @Date: 2020/7/9 15:11
 *  @Description:
 */
@Data
@TableName(value = "t_index")
public class Index {

    /**
     * 主键(数字id) 自动生成
     */
    @TableId(value="index_id",type= IdType.AUTO)
    private Integer id;

    /**
     *  @Param:指标名称
     */
    private String indexName;

    /**
     *  @Param:指标目标
     */
    private String indexTarget;

    /**
     *  @Param:备注
     */
    private String remarks;

    /**
     *  @Param:指标所属大类
     */
    private String indexClassification;

    /**
     *  @Param:评价
     */
    private String evaluation;

    /**
     *  @Param:检查记录
     */
    private String checkRecord;

    /**
     *  @Param:审核人
     */
    private String reviewer;

    /**
     *  @Param:审核时间
     */
    private Date reviewTime;

    /**
     *  @Param:资产id(外键)
     */
    private Integer assetId;
}
