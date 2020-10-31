package com.dandelion.management.service;

import com.dandelion.management.bean.EventInfo;

import java.util.List;
import java.util.Map;

public interface EventInfoService {

    /**
     * 通过多个条件进行派单管理的高级查询
     * @param options 多个条件
     * @return 查询结果
     */
    List<EventInfo> getDispatchEventInfoByOptions(Map<String,Object> options);
    /**
     * 通过id直接返回事件视图对象
     * @param id 唯一主键
     * @return 视图信息
     */
    EventInfo getEventInfoById(Integer id);
    /**
     * 通过多个条件进行高级查询
     * @param options 多个条件
     * @return 查询结果
     */
    List<EventInfo> getDecleationEventInfoByOptions(Map<String,Object> options);

    /**
     * 通过高级查询的条件完成一次详尽的检索
     * @param options 条件Map
     * @return 查询结果
     */
    List<EventInfo> getEventInfoByOptions(Map<String,Object> options);

    /**
     * 通过状态码返回所有当前状态的事件信息
     * @param status 状态码
     * @return 当前状态的事件
     */
    List<EventInfo> getEventInfoByStastus(Integer status);
}
