package com.dandelion.management.controller;

import com.dandelion.management.bean.ResponseResult;
import com.dandelion.management.bean.EventAssess;
import com.dandelion.management.bean.MyException;
import com.dandelion.management.service.EventAssessService;
import com.dandelion.management.utils.Final;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 事件与评论视图控制器
 *
 * @author hongjianYang
 * @date 2020/8/14
 */
@RestController
public class EventAssessController {
    @Autowired
    EventAssessService service;

    /**
     * 通过id查找一个评论信息 并完成相应的注入操作
     * @param assessId      id
     * @return              id对应的评论信息
     * @throws MyException  自定义异常
     */
    @RequestMapping(method = RequestMethod.GET,value = "/eventAssess/select/{assessId}")
    public ResponseResult<EventAssess> getEventAssessById(@PathVariable("assessId") Integer assessId) throws MyException {
        ResponseResult<EventAssess> info = new ResponseResult<>();
        EventAssess assess;
        assess = service.getEventAssessById(assessId);
        if (assess != null){
            info.setData(assess);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查找失败");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/eventAssess/select")
    public ResponseResult<List<EventAssess>> getEventsBySelectOptions(@RequestParam Map<String,Object> options) throws MyException {
        ResponseResult<List<EventAssess>> info = new ResponseResult<>();
        List<EventAssess> eventAssess = service.selectEventAssessByOptions(options);
        if (eventAssess != null){
            info.setData(eventAssess);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询结果为空");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/eventAssess/all")
    public ResponseResult<List<EventAssess>> getAllEventAssess() throws MyException {
        ResponseResult<List<EventAssess>> info = new ResponseResult<>();
        List<EventAssess> assesses = service.getAllEventAssess();
        if (assesses == null){
            throw new MyException("返回结果出错");
        }else{
            info.setCode(Final.OK);
            info.setData(assesses);
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/eventAssess/delete/{assessId}")
    public ResponseResult<Boolean> deleteById(@PathVariable("assessId") Integer assessId) throws MyException {
        ResponseResult<Boolean> info = new ResponseResult<>();
        Boolean data = service.deleteEventAssess(assessId);
        if (data){
            info.setCode(Final.OK);
            info.setData(data);
        }else{
            throw new MyException("删除失败??");
        }
        return info;
    }
}
