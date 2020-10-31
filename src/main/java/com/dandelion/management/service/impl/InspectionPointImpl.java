package com.dandelion.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dandelion.management.bean.EventInfo;
import com.dandelion.management.bean.InspectionPoint;
import com.dandelion.management.bean.Project;
import com.dandelion.management.mapper.InspectionPointMapper;
import com.dandelion.management.mapper.ProjectMapper;
import com.dandelion.management.service.InspectionPointService;
import com.dandelion.management.utils.Final;
//import com.mysql.cj.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InspectionPointImpl implements InspectionPointService {

    private static final String POINT_NUMBER = "pointNumber";
    private static final String COMPANY_ID = "companyId";
    private static final String POINT_ADDRESS = "pointAddress";



    private static final Integer UNDELETE = 0;

    private static final Integer UNINSERT = 0;

    private static final Integer UNUPDATE = 0;


    @Autowired
    InspectionPointMapper mapper;

    @Override
    public List<InspectionPoint> getAllInspectionPoint() {
        List<InspectionPoint> inspectionPoints = mapper.selectList(null);
        if(inspectionPoints.size() == 0){
            return null;
        }else{
            return inspectionPoints;
        }
    }

    @Override
    public InspectionPoint getInspectionPointInfoById(Integer inspectionPointId) {
        InspectionPoint inspectionPoint;
        inspectionPoint = mapper.selectById(inspectionPointId);
        if(inspectionPoint != null){
            return inspectionPoint;
        }else{
            return null;
        }
    }

    @Override
    public Boolean deleteInspectionPointById(Integer id) {
        int result = mapper.deleteById(id);
        if (result == UNDELETE) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean createInspectionPoint(InspectionPoint inspectionPoint) {
        int result = mapper.insert(inspectionPoint);
        if(result == UNINSERT){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Boolean updateInspectionPoint(InspectionPoint inspectionPoint) {
        int result = mapper.updateById(inspectionPoint);
        if (result == UNUPDATE) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean deleteByList(List<Integer> lists) {
        Integer num = mapper.deleteBatchIds(lists);
        if (num != 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<InspectionPoint> getInspectionPointInfoByOptions(Map<String, Object> options) {
        QueryWrapper<InspectionPoint> queryWrapper = new QueryWrapper<>();
        //编号
        if (options.get(POINT_NUMBER) != null && !Final.EMPTY_MSG.equals(options.get(POINT_NUMBER))){
            queryWrapper.like(Final.POINT_NUMBER_COLUMN,options.get(POINT_NUMBER));
        }
        //单位ID
        if (options.get(Final.COMPANY_ID) != null && !Final.EMPTY_MSG.equals(options.get(Final.COMPANY_ID))){
            queryWrapper.eq(Final.COMPANY_ID_COLUMN,options.get(Final.COMPANY_ID));
        }
        //地址
        if (options.get(POINT_ADDRESS) != null && !Final.EMPTY_MSG.equals(options.get(POINT_ADDRESS))){
            queryWrapper.like(Final.POINT_ADDRESS_COLUMN,options.get(POINT_ADDRESS));
        }

        List<InspectionPoint> projects = mapper.selectList(queryWrapper);
        return projects;
    }
}
