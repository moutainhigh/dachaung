package com.dandelion.management.service;

import com.dandelion.management.bean.EventAssess;

import java.util.List;
import java.util.Map;

/**
 * 事件与评论视图业务类
 * @author hongjianYang
 * @date 2020-8-16 15:23:44
 */
public interface EventAssessService {
    /**
     * 获取全部评论类信息
     * @return 评论信息
     */
    List<EventAssess> getAllEventAssess();

    /**
     * 对当前传入id实现单独删除
     * @param assessId  评价id
     * @return          删除结果
     */
    Boolean deleteEventAssess(Integer assessId);

    /**
     * 根据传入条件进行高级查询
     * @param options   传入条件的Map集合
     * @return          所有符合条件的EventAssess
     */
    List<EventAssess> selectEventAssessByOptions(Map<String,Object> options);

    /**
     * 通过id获取视图中一条数据
     * @param assessId  评论id
     * @return          评论数据信息
     */
    EventAssess getEventAssessById(Integer assessId);
}
