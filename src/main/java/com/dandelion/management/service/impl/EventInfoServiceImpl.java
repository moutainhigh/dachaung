package com.dandelion.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dandelion.management.bean.EventInfo;
import com.dandelion.management.mapper.EventInfoMapper;
import com.dandelion.management.service.EventInfoService;
import com.dandelion.management.utils.Final;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/7/29
 */
@Service
public class EventInfoServiceImpl implements EventInfoService {

    private static final String REPORTER_COMPANY = "reporterCompany";
    private static final String PROCESSOR_NAME = "processorName";
    private static final String PROCESSOR_NAME_COLUMN = "processor_name";
    private static final String PROCESSOR_COMPANY = "processorCompany";
    private static final String PROCESSOR_COMPANY_COLUMN = "processor_company";
    private static final String PROCESSOR_PHONE = "processorPhone";
    private static final String PROCESSOR_PHONE_COLUMN = "processor_phone";
    /**
     * 剥离所有魔法值
     */
//    private static final String PROJECT_NAME = "projectName";
//    private static final String PROJECT_NAME_COLUMN = "project_name";
//    private static final String MAINTAINS = "maintains";
//    private static final String MAINTENANCE_COMPANY = "maintenance_company";
//    private static final String LEVEL = "level";
//    private static final String STATUS = "status";
//    private static final String NOT_DISTRIBUTE = "未派单";
//    private static final String DISTRIBUTE = "已派单";
//    private static final String ASSESSED = "已处理";
//    private static final String NOT_ASSESSED = "已评价";
//    private static final String BEFORE_TIME = "beforeTime";
//    private static final String AFTER_TIME = "AfterTime";
//    private static final String REPORT_TIME = "report_time";
//    private static final String ALERT = "alert";
//    private static final String REMARK = "remark";
//    private static final String WRONG_TYPE = "wrongType";
//    private static final String FAULT_URGENCY = "fault_urgency";
//    private static final String EVENT_TYPE = "eventType";
//    private static final String FAULT_CLASSIFICATION = "fault_classification";
//    private static final String EMPTY_MSG = "";
//    private static final String REPORTING_METHOD = "reporting_method";
//    private static final String REPORTER_COMPANY_COLUMN = "reporter_company";
//    private static final String REPORTER_NAME = "reporterName";
//    private static final String REPORTER_NAME_COLUMN = "reporter_name";
//    private static final String REPORTER_PHONE = "reporterPhone";
//    private static final String REPORTER_PHONE_COLUMN = "reporter_phone";

    @Autowired
    EventInfoMapper mapper;

    /**
     * 通过多个条件进行高级查询
     *
     * @param options 多个条件
     * @return 查询结果
     */
    @Override
    public List<EventInfo> getDecleationEventInfoByOptions(Map<String, Object> options) {
        QueryWrapper<EventInfo> queryWrapper = new QueryWrapper<>();

        //项目名
        if (options.get(Final.PROJECT_NAME) != null && !Final.EMPTY_MSG.equals(options.get(Final.PROJECT_NAME))) {
            queryWrapper.eq(Final.PROJECT_NAME_COLUMN, options.get(Final.PROJECT_NAME));
        }

        //事件紧急度
        if (options.get(Final.LEVEL) != null && !Final.EMPTY_MSG.equals(options.get(Final.LEVEL))) {
            queryWrapper.eq(Final.FAULT_URGENCY, options.get(Final.LEVEL));
        }

        //日期间隔
        if (options.get(Final.BEFORE_TIME) != null && options.get(Final.AFTER_TIME) != null && !Final.EMPTY_MSG.equals(options.get(Final.BEFORE_TIME)) && !Final.EMPTY_MSG.equals(options.get(Final.AFTER_TIME))) {
            queryWrapper.between(Final.REPORT_TIME, options.get(Final.BEFORE_TIME), options.get(Final.AFTER_TIME));
        }

        //事件分类
        if (options.get(Final.WRONG_TYPE) != null && !Final.EMPTY_MSG.equals(options.get(Final.WRONG_TYPE))) {
            queryWrapper.eq(Final.FAULT_CLASSIFICATION, options.get(Final.WRONG_TYPE));
        }

        //事件类型
        if (options.get(Final.EVENT_TYPE) != null && !Final.EMPTY_MSG.equals(options.get(Final.EVENT_TYPE))) {
            queryWrapper.eq(Final.REPORTING_METHOD, options.get(Final.EVENT_TYPE));
        }

        //报单人单位
        if (options.get(REPORTER_COMPANY) != null && !Final.EMPTY_MSG.equals(options.get(REPORTER_COMPANY))) {
            queryWrapper.eq(Final.REPORTER_COMPANY_COLUMN, options.get(REPORTER_COMPANY));
        }

        //报单人名称
        if (options.get(Final.REPORTER_NAME) != null && !Final.EMPTY_MSG.equals(options.get(Final.REPORTER_NAME))) {
            queryWrapper.eq(Final.REPORTER_NAME_COLUMN, options.get(Final.REPORTER_NAME));
        }

        //报单人电话
        if (options.get(Final.REPORTER_PHONE) != null && !Final.EMPTY_MSG.equals(options.get(Final.REPORTER_PHONE))) {
            queryWrapper.eq(Final.REPORTER_PHONE_COLUMN, options.get(Final.REPORTER_PHONE));
        }

        //options = [{project=南阳市中心城区视频监控建设联网应用项目, reporterCompany=河南中道电子信息工程监理咨询有限公司, reporterName=曹总,
        // reporterPhone=1111, wrongType=前端设备故障, level=一般故障－12小时内, eventType=故障处理, beforeTime=2020/08/13 ,
        // AfterTime= 2020/09/16, page=1, limit=15}]

        List<EventInfo> eventInfo = mapper.selectList(queryWrapper);
        return eventInfo;
    }

    /**
     * 通过高级查询的条件完成一次详尽的检索
     *
     * @param options 条件Map
     * @return 查询结果
     */
    @Override
    public List<EventInfo> getEventInfoByOptions(Map<String, Object> options) {
        QueryWrapper<EventInfo> queryWrapper = new QueryWrapper<>();

        //项目名
        if (options.get(Final.PROJECT_NAME) != null && !Final.EMPTY_MSG.equals(options.get(Final.PROJECT_NAME))) {
            queryWrapper.eq(Final.PROJECT_NAME_COLUMN, options.get(Final.PROJECT_NAME));
        }

        //运维单位
        if (options.get(Final.MAINTAINS) != null && !Final.EMPTY_MSG.equals(options.get(Final.MAINTAINS))) {
            queryWrapper.eq(Final.MAINTENANCE_COMPANY, options.get(Final.MAINTAINS));
        }

        //报单人
        if (options.get("reporterName") != null && !Final.EMPTY_MSG.equals(options.get("reporterName"))) {
            queryWrapper.like("reporter_name", options.get("reporterName"));
        }

        //事件描述
        if (options.get("defectDes") != null && !Final.EMPTY_MSG.equals(options.get("defectDes"))) {
            queryWrapper.like("defect_description", options.get("defectDes"));
        }

        //事件紧急度
        if (options.get(Final.LEVEL) != null && !Final.EMPTY_MSG.equals(options.get(Final.LEVEL))) {
            queryWrapper.eq(Final.FAULT_URGENCY, options.get(Final.LEVEL));
        }

        //事件状态
        translateQueryStatus(options, queryWrapper);

        //日期间隔
        if (options.get(Final.BEFORE_TIME) != null && options.get(Final.AFTER_TIME) != null && !Final.EMPTY_MSG.equals(options.get(Final.BEFORE_TIME)) && !Final.EMPTY_MSG.equals(options.get(Final.AFTER_TIME))) {
            queryWrapper.between(Final.REPORT_TIME, options.get(Final.BEFORE_TIME), options.get(Final.AFTER_TIME));
        }

        //事件预警
        if (options.get(Final.ALERT) != null && !Final.EMPTY_MSG.equals(options.get(Final.ALERT))) {
            queryWrapper.eq(Final.ALERT, options.get(Final.ALERT));
        }

        //事件分类
        if (options.get(Final.WRONG_TYPE) != null && !Final.EMPTY_MSG.equals(options.get(Final.WRONG_TYPE))) {
            queryWrapper.eq(Final.FAULT_CLASSIFICATION, options.get(Final.WRONG_TYPE));
        }

        //事件类型
        if (options.get(Final.EVENT_TYPE) != null && !Final.EMPTY_MSG.equals(options.get(Final.EVENT_TYPE))) {
            queryWrapper.eq(Final.REPORTING_METHOD, options.get(Final.EVENT_TYPE));
        }

        //表单传回数据
        // [{project, user=报单人, status=未派单, maintains=, wrongType=浙江, level=3, alert=, eventType=故障处理, beforeTime=2020/08/05 , AfterTime= 2020/09/23, page=1, limit=15}]

        List<EventInfo> eventInfo = mapper.selectList(queryWrapper);
        return eventInfo;
    }

    /**
     * 通过状态码返回所有当前状态的事件信息
     *
     * @param status 状态码
     * @return 当前状态的事件
     */
    @Override
    public List<EventInfo> getEventInfoByStastus(Integer status) {
        Map<String, Object> options = new HashedMap<>();
        List<EventInfo> lists;

        //通过状态进行的条件查询
        if (status == 5) {
            options.put("status", 1);
            lists = mapper.selectByMap(options);
            options.put("status", 2);
            lists.addAll(mapper.selectByMap(options));
        } else {
            if (status != 0) {
                options.put("status", status);
                lists = mapper.selectByMap(options);
            } else {
                //若无参数则请求所有数据
                lists = mapper.selectList(null);
            }
        }
        return lists;
    }

    @Override
    public EventInfo getEventInfoById(Integer id) {
        EventInfo eventInfo;
        eventInfo = mapper.selectById(id);
        if (eventInfo != null) {
            return eventInfo;
        } else {
            return null;
        }
    }

    @Override
    public List<EventInfo> getDispatchEventInfoByOptions(Map<String, Object> options) {
        QueryWrapper<EventInfo> queryWrapper = new QueryWrapper<>();
        //派单人
        if (options.get(PROCESSOR_NAME) != null && !Final.EMPTY_MSG.equals(options.get(PROCESSOR_NAME))) {
            queryWrapper.eq(PROCESSOR_NAME_COLUMN, options.get(PROCESSOR_NAME));
        }

        //派单单位
        if (options.get(PROCESSOR_COMPANY) != null && !Final.EMPTY_MSG.equals(options.get(PROCESSOR_COMPANY))) {
            queryWrapper.eq(PROCESSOR_COMPANY_COLUMN, options.get(PROCESSOR_COMPANY));
        }

        //派单电话
        if (options.get(PROCESSOR_PHONE) != null && !Final.EMPTY_MSG.equals(options.get(PROCESSOR_PHONE))) {
            queryWrapper.eq(PROCESSOR_PHONE_COLUMN, options.get(PROCESSOR_PHONE));
        }

        //项目名
        if (options.get(Final.PROJECT_NAME) != null && !Final.EMPTY_MSG.equals(options.get(Final.PROJECT_NAME))) {
            queryWrapper.eq(Final.PROJECT_NAME_COLUMN, options.get(Final.PROJECT_NAME));
        }

        //运维单位
        if (options.get(Final.MAINTAINS) != null && !Final.EMPTY_MSG.equals(options.get(Final.MAINTAINS))) {
            queryWrapper.eq(Final.MAINTENANCE_COMPANY, options.get(Final.MAINTAINS));
        }

        //事件紧急度
        if (options.get(Final.LEVEL) != null && !Final.EMPTY_MSG.equals(options.get(Final.LEVEL))) {
            queryWrapper.eq(Final.FAULT_URGENCY, options.get(Final.LEVEL));
        }

        //事件状态
        translateQueryStatus(options, queryWrapper);

        //日期间隔
        if (options.get(Final.BEFORE_TIME) != null && options.get(Final.AFTER_TIME) != null && !Final.EMPTY_MSG.equals(options.get(Final.BEFORE_TIME)) && !Final.EMPTY_MSG.equals(options.get(Final.AFTER_TIME))) {
            queryWrapper.between(Final.REPORT_TIME, options.get(Final.BEFORE_TIME), options.get(Final.AFTER_TIME));
        }

        //事件预警
        if (options.get(Final.ALERT) != null && !Final.EMPTY_MSG.equals(options.get(Final.ALERT))) {
            queryWrapper.eq(Final.ALERT, options.get(Final.ALERT));
        }

        //事件分类
        if (options.get(Final.WRONG_TYPE) != null && !Final.EMPTY_MSG.equals(options.get(Final.WRONG_TYPE))) {
            queryWrapper.eq(Final.FAULT_CLASSIFICATION, options.get(Final.WRONG_TYPE));
        }

        //事件类型
        if (options.get(Final.EVENT_TYPE) != null && !Final.EMPTY_MSG.equals(options.get(Final.EVENT_TYPE))) {
            queryWrapper.eq(Final.REPORTING_METHOD, options.get(Final.EVENT_TYPE));
        }
        //使用Lamda表达式给自定义SQL增加括号
        queryWrapper.and(wrapper -> wrapper.eq(Final.STATUS, 2).or().eq(Final.STATUS, 3));

        List<EventInfo> eventInfo = mapper.selectList(queryWrapper);
        return eventInfo;
    }

    private void translateQueryStatus(Map<String, Object> options, QueryWrapper<EventInfo> queryWrapper) {
        if (options.get(Final.STATUS) != null && !Final.EMPTY_MSG.equals(options.get(Final.STATUS))) {
            String status = (String) options.get(Final.STATUS);
            if (Final.NOT_DISTRIBUTE.equals(status)) {
                queryWrapper.eq(Final.STATUS, "1");
            } else if (Final.DISTRIBUTE.equals(status)) {
                queryWrapper.eq(Final.STATUS, "2");
            } else if (Final.ASSESSED.equals(status)) {
                queryWrapper.eq(Final.STATUS, "3");
            } else if (Final.NOT_ASSESSED.equals(status)) {
                queryWrapper.eq(Final.STATUS, "4");
            }
        }
    }
}
