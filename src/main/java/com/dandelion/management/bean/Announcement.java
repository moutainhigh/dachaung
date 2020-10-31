package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 公告类
 *
 * @author hongjianYang
 * @date 2020/7/8
 */
@Data
@TableName("t_announcement")
public class Announcement {
    /**
     * 主键 自增
     */
    @TableId(value="announcement_id",type = IdType.AUTO)
    private Integer id;
    /**
     * @Param: 公告标题
     */
    private String announcementTitle;
    /**
     * @Param: 发布时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;
    /**
     * 发布人
     */
    private Integer publisherId;
    /**
     * 内容
     */
    private String content;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 附件文件id
     */
    private Integer fileId;
}
