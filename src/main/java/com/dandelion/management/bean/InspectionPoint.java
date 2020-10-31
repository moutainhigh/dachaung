package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *  @author: Sun Qingxin
 *  @Date: 2020/7/9 15:16
 *  @Description:
 */
@Data
@TableName("t_inspection_point")
public class InspectionPoint {
    /**
     * 主键(数字id) 自动生成
     */
    @TableId(value="point_id",type= IdType.AUTO)
    private Integer id;

    /**
     *  @Param:巡检点编号
     */
    private String pointNumber;

    /**
     *  @Param:巡检点
     */
    private String pointName;

    /**
     *  @Param:片区
     */
    private String region;

    /**
     *  @Param:地址
     */
    private String pointAddress;

    /**
     *  @Param:隶属项目ID
     */
    private Integer projectId;

    /**
     *  @Param:建设单位ID
     */
    private Integer companyId;

    /**
     * @Param:资产ID
     */
    private Integer assetId;

    /**
     *  @Param:经度
     */
    private double longitude;

    /**
     *  @Param:纬度
     */
    private double latitude;

    /**
     *  @Param:备注
     */
    private String remark;
}
