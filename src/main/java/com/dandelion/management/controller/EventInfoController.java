package com.dandelion.management.controller;

import com.dandelion.management.bean.ResponseResult;
import com.dandelion.management.bean.EventInfo;
import com.dandelion.management.bean.MyException;
import com.dandelion.management.mapper.EventInfoMapper;
import com.dandelion.management.service.EventInfoService;
import com.dandelion.management.utils.Final;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/7/27
 */
@RestController
public class EventInfoController {
    @Autowired
    EventInfoMapper mapper;

    @Autowired
    EventInfoService service;

    @RequestMapping(method = RequestMethod.GET,value = "/eventInfo/select/dispatch")
    public ResponseResult<List<EventInfo>> getDispatchEventsBySelectOptions(@RequestParam Map<String,Object> options) throws MyException {
        ResponseResult<List<EventInfo>> info = new ResponseResult<>();
        List<EventInfo> eventInfo = service.getDispatchEventInfoByOptions(options);
        if (eventInfo != null){
            info.setData(eventInfo);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询结果为空");
        }
        return info;
    }
    
    /**
     * 通过id直接返回视图中所有信息
     * @param id 主键id
     * @return 事件所有信息
     * @throws MyException 统一异常处理
     */
    @RequestMapping(method = RequestMethod.GET,value = "/eventInfo/select/{id}")
    public ResponseResult<EventInfo> getEventInfoById(@PathVariable("id")Integer id) throws MyException {
        ResponseResult<EventInfo> info = new ResponseResult<>();
        EventInfo eventInfo = service.getEventInfoById(id);
        if (eventInfo != null){
            info.setData(eventInfo);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询结果为空");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/eventInfo/select/declaration")
    public ResponseResult<List<EventInfo>> getEventsBySelectOptions(@RequestParam Map<String,Object> options) throws MyException {
        ResponseResult<List<EventInfo>> info = new ResponseResult<>();
        List<EventInfo> eventInfo = service.getDecleationEventInfoByOptions(options);
        if (eventInfo.size() != 0){
            info.setData(eventInfo);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询结果为空");
        }
        return info;
    }

    /**
     * 通过status获取事件
     * @param status 事件状态码
     * @return 事件集合
     */
    @RequestMapping(method = RequestMethod.GET,value = "/events/option/{status}")
    public ResponseResult<List<EventInfo>> getEventsByStatus(@PathVariable("status") Integer status) throws MyException {
        List<EventInfo> list = service.getEventInfoByStastus(status);
        ResponseResult<List<EventInfo>> info = new ResponseResult<>();
        if (list != null){
            info.setData(list);
            info.setCode(Final.OK);
        }else{
            throw new MyException("未查询到信息 或查询数据为0");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/eventInfo/all")
    public ResponseResult<List<EventInfo>> getEventInfos(){
        ResponseResult<List<EventInfo>> info = new ResponseResult<>();
        info.setCode(Final.OK);
        info.setData(mapper.selectList(null));

        return info;
    }

    /**
     * 报单管理页面的多条件查询 (不可复用)
     * @param options 查询条件
     * @return 查询结果
     * @throws MyException 自动异常捕捉类
     */
    @RequestMapping(method = RequestMethod.GET,value = "/eventInfo/select")
    public ResponseResult<List<EventInfo>> getEventInfoByOptions(@RequestParam Map<String,Object> options) throws MyException {
        ResponseResult<List<EventInfo>> info = new ResponseResult<>();
        List<EventInfo> eventInfo = service.getEventInfoByOptions(options);
        if (eventInfo.size() != 0){
            info.setData(eventInfo);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询结果为空");
        }
        return info;
    }
}
