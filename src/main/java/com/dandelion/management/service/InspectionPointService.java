package com.dandelion.management.service;

import com.dandelion.management.bean.EventInfo;
import com.dandelion.management.bean.InspectionPoint;
import com.dandelion.management.bean.Project;

import java.util.List;
import java.util.Map;

public interface InspectionPointService {

    List<InspectionPoint> getAllInspectionPoint();

    InspectionPoint getInspectionPointInfoById(Integer inspectionPointId);

    Boolean deleteInspectionPointById(Integer id);

    Boolean createInspectionPoint(InspectionPoint inspectionPoint);

    Boolean updateInspectionPoint(InspectionPoint inspectionPoint);

    Boolean deleteByList(List<Integer> lists);

    List<InspectionPoint> getInspectionPointInfoByOptions(Map<String, Object> options);

}
