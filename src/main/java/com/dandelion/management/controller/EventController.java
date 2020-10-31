package com.dandelion.management.controller;

import com.dandelion.management.bean.Asset;
import com.dandelion.management.bean.ResponseResult;
import com.dandelion.management.bean.Event;
import com.dandelion.management.bean.MyException;
import com.dandelion.management.service.EventAssessService;
import com.dandelion.management.service.EventService;
import com.dandelion.management.utils.Final;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 事件类的Restful API
 *
 * @author hongjianYang
 * @version 2020/7/20 22:20:56
 * @date 2020/7/14
 */
@RestController("/events")
public class EventController {
    @Autowired
    EventService service;

    @Autowired
    EventAssessService assessService;

    @GetMapping("/events/s/assetByProject/{projectId}")
    public ResponseResult<List<Asset>> getAssetByProject(@PathVariable("projectId") Integer projectId) throws MyException {
        ResponseResult<List<Asset>> info = new ResponseResult<>();
        List<Asset> assets = service.selectAssetByProject(projectId);
        if (assets != null){
            info.setData(assets);
            info.setCode(Final.OK);
        }else{
            throw new MyException("获取id:" + projectId + "下的数据失败!");
        }
        return info;
    }

    /**
     * 根据前端传回的资产更换信息对资产进行更换操作
     * @param assets 资产信息列表
     * @return 资产更新是否完成
     */
    @PostMapping("/events/f/useasset")
    public ResponseResult<Boolean> useAssset(@RequestBody List<Asset> assets) throws MyException {
        System.out.println("assets = [" + assets + "]");
        ResponseResult<Boolean> info = new ResponseResult<>();
        Boolean res = service.dedlineAssets(assets);
        if (res){
            info.setCode(Final.OK);
            info.setData(res);
        }else{
            throw new MyException("资产更新失败");
        }
        return info;
    }

    /**
     * 根据前端所传List对后端进行删除
     * @return 统一返回数据格式
     */
    @RequestMapping(method = RequestMethod.POST,value = "/events/f/deletes")
    public ResponseResult<Boolean> deleteEventsByList(@RequestParam("lists[]") final List<Integer> lists) throws MyException {
        final ResponseResult<Boolean> info = new ResponseResult<>();
        final Boolean res = service.deleteByList(lists);
        if (res){
            info.setCode(Final.OK);
            info.setData(true);
        }else{
            throw new MyException("批量删除失败");
        }
        return info;
    }

    /**
     * 根据表单各种条件查询对应的事件 未完成
     * 不增加RequestParam注解 无法正常获取数据
     * @return 对应的事件数据
     */
    @RequestMapping(method = RequestMethod.GET, value = "/events/f/select/")
    @ResponseBody
    public ResponseResult<List<Event>> getEventsByOptions(@RequestParam final Map<String,Object> options) throws Exception{
        System.out.println("options = [" + options + "]");
        final List<Event> events = service.getEventsByOptions(options);
        final ResponseResult<List<Event>> info = new ResponseResult<>();
        if (events.size() == 0){
            throw new MyException("查询结果为空");
        }else{
            info.setCode(Final.OK);
            info.setData(events);
        }
        return info;
    }
    
    /**
     * 通过id删除事件 无论处于任何状态均能直接完成删除
     *
     * @param id id
     * @return 是否删除成功
     */
    @RequestMapping(method = RequestMethod.POST, value = "/events/f/delete/{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable(value = "id") final Integer id) throws MyException {
        final ResponseResult<Boolean> info = new ResponseResult<>();
        final Boolean b = service.deleteById(id);
        if (!b) {
            throw new MyException("删除失败");
        } else {
            info.setCode(Final.OK);
            info.setData(true);
        }
        return info;
    }

    /**
     * 前端JSON需将表单中全部数据提交
     *
     * @param event 需要更新的事件对象(必须包含事件id)
     * @return 是否更新成功
     * @throws MyException 是否更新成功
     */
    @RequestMapping(method = RequestMethod.POST, value = "/events/f/update")
    public ResponseResult<Boolean> updateEvent(final Event event) throws MyException {
        final ResponseResult<Boolean> info = new ResponseResult<>();
        final Boolean b = service.updateEvent(event);
        if (!b) {
            throw new MyException("数据更新失败,请查看数据类型是否正确");
        } else {
            info.setCode(Final.OK);
            info.setData(true);
        }
        return info;
    }

    /**
     * 用于初次派单的添加 以后则为更新
     *
     * @param event 事件
     * @return 是否创建成功
     */
    @RequestMapping(method = RequestMethod.POST, value = "/events/f/create")
    public ResponseResult<Boolean> createEvent(final Event event,final String reporterName,final String reporterPhone,final String reporterCompany) throws MyException {
        final ResponseResult<Boolean> info = new ResponseResult<>();
        final Boolean b = service.createEvent(event,reporterName,reporterPhone,reporterCompany);
        if (!b) {
            throw new MyException("创建事件失败");
        } else {
            info.setCode(Final.OK);
            info.setData(true);
        }
        return info;
    }

    /**
     * 用于评价
     *
     * @param event 事件
     * @return 是否创建成功
     */
    @RequestMapping(method = RequestMethod.POST, value = "/events/f/assess")
    public ResponseResult<Boolean> assessEvent(final Event event,final String comment,final Integer level) throws MyException {
        System.out.println("event = [" + event + "], comment = [" + comment + "], level = [" + level + "]");
        final ResponseResult<Boolean> info = new ResponseResult<>();
        Boolean res = service.assessEvent(event,comment,level);
        if (res){
            info.setData(Boolean.TRUE);
            info.setCode(Final.OK);
            info.setMessage("评论成功");
        }else{
            throw new MyException("评论失败");
        }
        return info;
    }

    /**
     * 用于结案
     *
     * @param event 事件
     * @return 是否创建成功
     */
    @RequestMapping(method = RequestMethod.POST, value = "/events/f/complete")
    public ResponseResult<Boolean> completeEvent(final Event event,final String processingDescription,final String processorName,final String processorPhone,final String processorCompany) throws MyException {
        final ResponseResult<Boolean> info = new ResponseResult<>();
        System.out.println("event = [" + event + "], processingDescription = [" + processingDescription + "], processorName = [" + processorName + "], processorPhone = [" + processorPhone + "], processorCompany = [" + processorCompany + "]");
        final Boolean b = service.completeEvent(event,processingDescription,processorName,processorPhone,processorCompany);
        if (!b) {
            throw new MyException("结案失败");
        } else {
            info.setCode(Final.OK);
            info.setData(true);
        }
        return info;
    }

    /**
     * 用于派单 以后则为更新
     *
     * @param event 事件
     * @return 是否创建成功
     */
    @RequestMapping(method = RequestMethod.POST, value = "/events/f/dispatch")
    public ResponseResult<Boolean> dispatchEvent(final Event event,final String maintenanceName,final String maintenancePhone,final String maintenanceCompany) throws MyException {
        final ResponseResult<Boolean> info = new ResponseResult<>();
        final Boolean b = service.updateEvent(event,maintenanceName,maintenancePhone,maintenanceCompany);
        if (!b) {
            throw new MyException("派单失败");
        } else {
            info.setCode(Final.OK);
            info.setData(true);
        }
        return info;
    }

    /**
     * @param id 通过需要查询事件的id
     * @return 事件json
     */
    @RequestMapping(method = RequestMethod.GET, value = "/events/{id}")
    public ResponseResult<Event> getEventById(@PathVariable(value = "id") final Integer id) throws MyException {
        final Event e = service.getEventById(id);
        final ResponseResult<Event> info = new ResponseResult<>();
        if (e == null) {
            throw new MyException("查无此事件");
        } else {
            //请求成功时放入状态码与数据对象(统一JSON格式进行返回)
            info.setCode(Final.OK);
            info.setData(e);
        }
        return info;
    }

    /**
     * 通过状态返回对应数据列表
     *
     * @param status 当前状态
     * @return 数据列表
     * @throws MyException 自定义异常
     */
    @RequestMapping(method = RequestMethod.GET, value = "/events/s/{status}")
    public ResponseResult<List<Event>> getEventsByStatus(@PathVariable(value = "status") final String status) throws MyException {
        final List<Event> events = service.getEventsByStatus(status);
        final ResponseResult<List<Event>> info = new ResponseResult<>();
        if (events.size() == 0) {
            throw new MyException("当前状态无对应事件");
        } else {
            info.setCode(Final.OK);
            info.setData(events);
        }
        return info;
    }


}
