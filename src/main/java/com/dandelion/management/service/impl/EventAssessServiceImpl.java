package com.dandelion.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dandelion.management.bean.EventAssess;
import com.dandelion.management.mapper.AssessMapper;
import com.dandelion.management.mapper.EventAssessMapper;
import com.dandelion.management.service.EventAssessService;
import com.dandelion.management.utils.Final;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Class description
 *
 * @author hongjian Yang
 * @date 2020/8/14
 */
@Service
public class EventAssessServiceImpl implements EventAssessService {
    public static final int UNDELETE = 0;
    @Autowired
    EventAssessMapper mapper;
    @Autowired
    AssessMapper assessMapper;

    /**
     * 获取全部评论类信息
     *
     * @return 评论信息
     */
    @Override
    public List<EventAssess> getAllEventAssess() {
        QueryWrapper<EventAssess> qw = new QueryWrapper<>();
        qw.eq(Final.STATUS,3).or().eq(Final.STATUS,4);
        return mapper.selectList(qw);
    }

    /**
     * 对当前传入id实现单独删除
     *
     * @param assessId 评价id
     * @return 删除结果
     */
    @Override
    public Boolean deleteEventAssess(Integer assessId) {
        Integer res = assessMapper.deleteById(assessId);
        if (res == UNDELETE){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 根据传入条件进行高级查询
     *
     * @param options 传入条件的Map集合
     * @return 所有符合条件的EventAssess
     */
    @Override
    public List<EventAssess> selectEventAssessByOptions(Map<String, Object> options) {
        QueryWrapper<EventAssess> queryWrapper = new QueryWrapper<>();
        //派单人
        if(options.get("commentatorName") != null && !Final.EMPTY_MSG.equals(options.get("commentatorName"))){
            queryWrapper.eq("commentator_name",options.get("commentatorName"));
        }

        //派单单位
        if(options.get("commentatorCompany") != null && !Final.EMPTY_MSG.equals(options.get("commentatorCompany"))){
            queryWrapper.eq("commentator_company",options.get("commentatorCompany"));
        }

        //派单电话
        if(options.get("commentatorPhone") != null && !Final.EMPTY_MSG.equals(options.get("commentatorPhone"))){
            queryWrapper.eq("commentator_phone",options.get("commentatorPhone"));
        }

        //项目名
        if (options.get(Final.PROJECT_NAME) != null && !Final.EMPTY_MSG.equals(options.get(Final.PROJECT_NAME))){
            queryWrapper.eq(Final.PROJECT_NAME_COLUMN,options.get(Final.PROJECT_NAME));
        }

        //运维单位
        if (options.get(Final.MAINTAINS) != null && !Final.EMPTY_MSG.equals(options.get(Final.MAINTAINS))){
            queryWrapper.eq(Final.MAINTENANCE_COMPANY,options.get(Final.MAINTAINS));
        }

        //事件紧急度
        if(options.get(Final.LEVEL) != null && !Final.EMPTY_MSG.equals(options.get(Final.LEVEL))){
            queryWrapper.eq(Final.FAULT_URGENCY, options.get(Final.LEVEL));
        }

        //日期间隔
        if(options.get(Final.BEFORE_TIME) != null && options.get(Final.AFTER_TIME) != null  && !Final.EMPTY_MSG.equals(options.get(Final.BEFORE_TIME))  && !Final.EMPTY_MSG.equals(options.get(Final.AFTER_TIME))){
            queryWrapper.between("rating_time", options.get(Final.BEFORE_TIME), options.get(Final.AFTER_TIME));
        }

        //事件预警
        if(options.get(Final.ALERT) != null && !Final.EMPTY_MSG.equals(options.get(Final.ALERT))){
            queryWrapper.eq(Final.ALERT,options.get(Final.ALERT));
        }

        //事件分类
        if(options.get(Final.WRONG_TYPE) != null && !Final.EMPTY_MSG.equals(options.get(Final.WRONG_TYPE))){
            queryWrapper.eq(Final.FAULT_CLASSIFICATION,options.get(Final.WRONG_TYPE));
        }

        //事件类型
        if(options.get(Final.EVENT_TYPE)!= null && !Final.EMPTY_MSG.equals(options.get(Final.EVENT_TYPE))){
            queryWrapper.eq(Final.REPORTING_METHOD,options.get(Final.EVENT_TYPE));
        }
        //使用Lamda表达式给自定义SQL增加括号
        queryWrapper.and(wrapper->wrapper.eq(Final.STATUS,3).or().eq(Final.STATUS,4));

        List<EventAssess> eventAssess = mapper.selectList(queryWrapper);
        return eventAssess;
    }

    /**
     * 通过id获取视图中一条数据
     *
     * @param assessId  评论id
     * @return          评论数据信息
     */
    @Override
    public EventAssess getEventAssessById(Integer assessId) {
        return mapper.selectById(assessId);
    }

}
