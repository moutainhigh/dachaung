package com.dandelion.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dandelion.management.bean.EventInfo;
import com.dandelion.management.bean.InspectionPoint;
import com.dandelion.management.bean.Project;
import com.dandelion.management.mapper.ProjectMapper;
import com.dandelion.management.service.ProjectService;
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
 * @date 2020/7/27
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    private static final String PROJECT_ID = "projectId";
    private static final String PROJECT_NAME = "projectName";
    private static final String PRINCIPAL = "principal";
    private static final String PRINCIPAL_PHONE = "principalPhone";
    private static final String COMPANY_ID = "companyId";


    private static final Integer UNDELETE = 0;

    private static final Integer UNINSERT = 0;

    private static final Integer UNUPDATE = 0;


    @Autowired
    ProjectMapper mapper;

    /**
     * 获取Id及Name属性
     *
     * @param id Project唯一主键
     * @return 带有Id属性, Name属性的HashMap若未查询到,则返回null
     */
    @Override
    public Map<String, Object> getProjectIdAndNameById(Integer id) {
        Project project = mapper.selectById(id);
        Map<String, Object> result = new HashedMap<>();
        if (project == null) {
            return null;
        } else {
            result.put(PROJECT_ID, project.getProjectId());
            result.put(PROJECT_NAME, project.getProjectName());
        }
        return result;
    }
    @Override
    public List<Project> getAllProject() {
        List<Project> projects = mapper.selectList(null);
        if(projects.size() == 0){
            return null;
        }else{
            return projects;
        }
    }

    @Override
    public Boolean deleteProjectById(Integer id) {
        int result = mapper.deleteById(id);
        if (result == UNDELETE) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean createProject(Project project) {
        int result = mapper.insert(project);

        if(result == UNINSERT){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Boolean updateProject(Project project) {
        int result = mapper.updateById(project);
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
    public List<Project> getProjectInfoByOptions(Map<String, Object> options) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();

        //项目名
        if (options.get(Final.PROJECT_NAME) != null && !Final.EMPTY_MSG.equals(options.get(Final.PROJECT_NAME))){
            queryWrapper.like(Final.PROJECT_NAME_COLUMN,options.get(Final.PROJECT_NAME));
        }

        //项目编号（id
        if (options.get(Final.PROJECT_ID) != null && !Final.EMPTY_MSG.equals(options.get(Final.PROJECT_ID))){
            queryWrapper.like(Final.PROJECT_ID_COLUMN,options.get(Final.PROJECT_ID));
        }

        //单位ID
        if (options.get(Final.COMPANY_ID) != null && !Final.EMPTY_MSG.equals(options.get(Final.COMPANY_ID))){
            queryWrapper.eq(Final.COMPANY_ID_COLUMN,options.get(Final.COMPANY_ID));
        }
        //电话
        if (options.get(PRINCIPAL_PHONE) != null && !Final.EMPTY_MSG.equals(options.get(PRINCIPAL_PHONE))){
            queryWrapper.like(Final.PRINCIPAL_PHONE_COLUMN,options.get(PRINCIPAL_PHONE));
        }
        //负责人
        if (options.get(PRINCIPAL) != null && !Final.EMPTY_MSG.equals(options.get(PRINCIPAL))){
            queryWrapper.like(Final.PRINCIPAL_COLUMN,options.get(PRINCIPAL));
        }

        List<Project> projects = mapper.selectList(queryWrapper);
        return projects;
    }

    @Override
    public Project getProjectInfoById(Integer projectId) {
        Project project;
        project = mapper.selectById(projectId);
        if(project != null){
            return project;
        }else{
            return null;
        }
    }
}
