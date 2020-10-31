package com.dandelion.management.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.PrimitiveIterator;

/**
 *  @author: Sun Qingxin
 *  @Date: 2020/7/9 13:33
 *  @Description:
 */ 
@Data
@TableName(value = "t_event_protocol")
public class EventProtocol {
    
    
    /**
     *  @Param: 
     */ 
    @TableId(value = "event_protocol_id",type = IdType.AUTO)
    private Integer id;
    

    /**
     *  @Param:事件协议分类
     */
    private String eventProtocolType;


    /**
     *  @Param:协议小时
     */
    private Integer eventHours;

    /**
     *  @Param:协议分钟
     */
    private Integer eventMinutes;

    /**
     *  @Param:协议描述
     */
    private String protocolDescription;

    private String remark;
}
