package com.dandelion.management.service;

import com.dandelion.management.bean.ResponseResult;
import com.dandelion.management.bean.EventInfo;
import com.dandelion.management.bean.Project;

import java.util.List;
import java.util.Map;

/**
 * 项目实体的服务接口
 *
 * @author hongjianYang
 * @date 2020/7/27
 */
public interface ProjectService {


    /**
     * 获取Id及Name属性
     * @param id Project唯一主键
     * @return 带有Id属性,Name属性的HashMap 若未查询到,则返回null
     */
    Map<String,Object> getProjectIdAndNameById(Integer id);


    /**                                n
     * 返回所有的Project信息
     * @return 返回所有的Project信息以供选择
     */
    List<Project> getAllProject();

    Boolean deleteProjectById(Integer id);

    Boolean createProject(Project project);

    Boolean updateProject(Project project);

    Boolean deleteByList(List<Integer> lists);

    List<Project> getProjectInfoByOptions(Map<String, Object> options);

    Project getProjectInfoById(Integer projectId);
}
