package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * @author: Sun Qingxin
 * @Date: 2020/7/9 15:04
 * @Description:
 */
@Data
@TableName(value = "t_implement")
public class Implement {
    /**
     * 主键(数字id) 自动生成
     */
    @TableId(value = "implement_id", type = IdType.AUTO)
    private Integer id;

    /**
     *  @Param:信息点id
     */
    private Integer inspectionPointId;

    /**
     *  @Param:地址
     */
    @TableField(value = "implement_address")
    private String address;

    /**
     *  @Param:项目id
     */
    private Integer projectId;

    /**
     *  @Param:承建单位id
     */
    private Integer companyId;

    /**
     *  @Param:进度状态
     */
    private String status;

    /**
     *  @Param:附件（多个图片使用分号隔开）
     */
    private String fileId;

    /**
     *  @Param:审核人
     */
    private Integer reviewer;

    /**
     *  @Param:审核说明
     */
    private String paramDes;

    /**
     *  @Param:时间
     */
    private Date implementTime;

    /**
     *  @Param:备注
     */
    private String remark;

}
