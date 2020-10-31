package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 上传图片实体类
 *
 * @author hongjian Yang
 * @date 2020/8/18
 */
@TableName("t_image")
@Data
public class ImageFile {
    /**
     * 主键 唯一id
     */
    @TableId(value = "image_file_id", type = IdType.AUTO)
    private Integer imageFileId;
    /**
     * 事件id
     */
    private Integer eventId;
    /**
     * 事件状态
     */
    private Integer eventStatus;
    /**
     * 上传时间
     */
    private Timestamp uploadTime;
    /**
     * 备注 冗余列
     */
    private String remark;
}
