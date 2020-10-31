package com.dandelion.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dandelion.management.bean.Event;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 *  @author: Sun Qingxin
 *  @Date: 2020/7/9 15:31
 *  @Description:
 */
public interface EventMapper extends BaseMapper<Event> {
    @Select("select event_id AS id,project_id," +
            "reporter_id,processor_id,contractor_id," +
            "peration_maintenance_id AS operationId," +
            "report_time,address,defect_description AS defectDes," +
            "processing_description AS processingDes,reporting_method," +
            "fault_classification,fault_urgency,longitude,latitude," +
            "fault_area,audit_opinion,obstacle_report_id,approval_status," +
            "dispatch_unit,reviewer,scene_description,approach," +
            "maintenance_recommendations,on_site_documents,evaluation," +
            "level,timestamp,processing_time,remind,remark," +
            "status from t_event where report_time > #{beforeTime} and  report_time < #{AfterTime}")
    List<Event> selectByOptions(Map<String,Object> options);
}
