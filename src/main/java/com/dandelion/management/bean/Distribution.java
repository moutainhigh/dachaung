package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *  @author: Sun Qingxin
 *  @Date: 2020/7/9 12:39
 *  @Description:  bean层
 */

@Data
@TableName("t_distribution")
public class Distribution {


    /**
     *  @Param: 主键id
     */
    @TableId(value="distribution_id",type= IdType.AUTO)
    private Integer id;


    /**
     *  @Param: 信息点ID
     */
    @TableField(value = "inspection_point_id")
    private Integer inspectionsId;

    /**
     *  @Param:资产ID
     */
    private Integer assetId;

    /**
     *  @Param: 备注
     */
    private String remark;



}
