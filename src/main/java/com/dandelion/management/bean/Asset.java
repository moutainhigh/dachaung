package com.dandelion.management.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Asset entity
 *
 * @author hongjianYang
 * @date 2020/7/8
 */
@Data
@TableName("t_asset")
public class Asset {
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 资产id
     */
    @TableId(value = "asset_id",type = IdType.AUTO)
    private Integer assetId;
    /**
     * 资产编号
     */
    private String assetNumber;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 资产品牌
     */
    private String assetBrand;
    /**
     * 资产型号
     */
    private String assetModel;
    /**
     * 生产厂商
     */
    private String manufacturer;
    /**
     * 厂址
     */
    private String factorySite;
    /**
     * 计量单位
     */
    private String unit;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 单位价格
     */
    private BigDecimal unitPrice;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 资产类型
     */
    private String assetType;
    /**
     * 采购日期
     */
    private Date purchaseDate;
    /**
     * 采购渠道
     */
    private String wayBuying;
    /**
     * 采购类型
     */
    private String typeBuying;
    /**
     * 质保类型
     */
    private String warrantyType;
    /**
     * 质保等级
     */
    private String warrantyLevel;
    /**
     * 质保天数
     */
    private Integer warrantyDays;
    /**
     * 质保日期至
     */
    private Date warrantyDateTo;
    /**
     * 参数说明
     */
    private String parameterDescription;
    /**
     * 资产状态
     */
    private String assetState;
    /**
     * 运维单位
     */
    private String maintenanceUnit;
    /**
     * 类别
     */
    private String assetCategory;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 维修情况
     */
    private String maintainceState;
}
