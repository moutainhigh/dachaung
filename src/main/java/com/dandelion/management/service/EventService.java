package com.dandelion.management.service;

import com.dandelion.management.bean.Asset;
import com.dandelion.management.bean.Event;

import java.util.List;
import java.util.Map;


/**
 * 事件 Service 接口
 *
 * @author hongjianYang
 * @date 2020/7/14
 */
public interface EventService {
    /**
     * 根据项目编号返回项目中所使用的资产信息
     * @param   projectId     项目id
     * @return  项目所涉及的资产信息
     */
    List<Asset> selectAssetByProject(Integer projectId);

    Boolean deleteByList(List<Integer> lists);

    /**
     * 条件查询
     * @param options 条件
     * @return 查询结果
     */
    List<Event> getEventsByOptions(Map<String,Object> options) throws Exception;

    Boolean deleteById(Integer id);

    /**
     * 将全部数据封装为JSON直接对整个对象进行更新操作
     *
     * @param event 事件对象
     * @return 是否更新成功
     */
    Boolean updateEvent(Event event);

    /**
     * 通过id向后端数据库发起查询
     *
     *@param id 通过需要查询事件的id
     * @return 事件对象
     */
    Event getEventById(Integer id);

    /**
     * 根据事件状态返回事件数据
     *
     * @param status 状态
     * @return 事件数据List
     */
    List<Event> getEventsByStatus(String status);

    /**
     * 根据Form提交数据进行创建
     *
     * @param event Form数据
     * @return 是否创建成功
     */
    Boolean createEvent(Event event,String reporterName,String reporterPhone,String reporterUnit);
    
    /**
     * 根据提交的表单数据进行更新(下一层进行抽象)
     * @param event             事件对象
     * @param processorName     派单人姓名
     * @param processorPhone    派单人电话
     * @param processorUnit     派单人公司
     * @return
     */
    Boolean updateEvent(Event event,String processorName,String processorPhone,String processorUnit);
    
    /**
     * 根据提交的表单进行信息注入 结案管理
     * @param event                     事件对象
     * @param processingDescription     处理描述
     * @param processorName             处理人
     * @param processorCompany          处理单位
     * @param processorPhone            处理人手机
     * @return
     */
    Boolean completeEvent(Event event,String processingDescription,String processorName,String processorPhone,String processorCompany);

    /**
     * 根据提交的表单进行更新操作
     * @param event     事件对象
     * @param common    评论
     * @param level     评级
     * @return          返回是否更新成功
     */
    Boolean assessEvent(final Event event,final String common,final Integer level);

    /**
     * 更改资产数量 单向降低操作
     * @param assets    资产
     * @return          是否降低成功
     */
    Boolean dedlineAssets(List<Asset> assets);
}
