package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * DataDoc entity
 *
 * @author hongjianYang
 * @date 2020/7/8
 */
@Data
@TableName("t_datadoc")
public class DataDoc {
    /**
     * 主键 自增
     */
    @TableId(value = "datadoc_id",type = IdType.AUTO)
    private Integer datadocId;
    /**
     * 类别名称
     */
    private String typeName;
    /**
     * 字典名称
     */
    private String docName;
    /**
     * 字典值
     */
    private Integer docNumber;
    /**
     * 备注
     */
    private String remark;
}
