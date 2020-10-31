package com.dandelion.management.controller;

import com.dandelion.management.bean.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dandelion.management.bean.ResponseResult;
import com.dandelion.management.bean.MyException;
import com.dandelion.management.service.ProjectService;
import com.dandelion.management.utils.Excel;
import com.dandelion.management.utils.ImageUploadUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dandelion.management.service.ProjectService;

import com.dandelion.management.utils.Final;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目控制器
 * 事件实体类必须下游接口之一
 * @author hongjianYang
 * @date 2020/7/27
 */
@RestController
public class ProjectController {
    @Autowired
    ProjectService service;
    /**
     * 获取所有项目数据
     * @return 返回统一格式的返回值
     * @throws MyException 若发生异常会统一进行拦截
     */
    @RequestMapping(method = RequestMethod.GET,value = "/project/all")
    public ResponseResult<List<Project>> getAllProject() throws MyException{
        ResponseResult<List<Project>> info = new ResponseResult<>();
        List<Project> projects = service.getAllProject();
        if (projects != null) {
            info.setData(projects);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询项目数据失败");
        }

        return info;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/projects/select/{id}")
    public ResponseResult<Project> getProjectById(@PathVariable("id")Integer projectId) throws MyException {
        ResponseResult<Project> info = new ResponseResult<>();
        Project project = service.getProjectInfoById(projectId);
        if (project != null){
            info.setData(project);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询结果为空");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/projects/delete/{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable(value = "id") final Integer id) throws MyException {
        ResponseResult<Boolean> info = new ResponseResult<>();
        Boolean b = service.deleteProjectById(id);
        if (!b) {
            throw new MyException("删除失败");
        } else {
            info.setCode(ResponseResult.OK);
            info.setData(b);
        }
        return info;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/projects/deletes")
    public ResponseResult<Boolean> deleteProjectsByList(@RequestParam("lists[]") final List<Integer> lists) throws MyException {
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
     * 项目创建
     * @param project
     * @return
     * @throws MyException
     */
    @RequestMapping(method = RequestMethod.POST,value = "/projects/create")
    public ResponseResult<Boolean> createProject(@RequestBody Project project) throws MyException {
        ResponseResult<Boolean> ResponseResult = new ResponseResult<>();
        Boolean b = service.createProject(project);
        if (!b) {
            throw new MyException("创建项目失败");
        } else {
            ResponseResult.setCode(ResponseResult.OK);
            ResponseResult.setData(b);
        }
        return ResponseResult;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/projects/update")
    public ResponseResult<Boolean> updateEvent(@RequestBody Project project) throws MyException {
        final ResponseResult<Boolean> info = new ResponseResult<>();
        final Boolean b = service.updateProject(project);
        if (!b) {
            throw new MyException("数据更新失败,请查看数据类型是否正确");
        } else {
            info.setCode(Final.OK);
            info.setData(true);
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/projects/select")
    public ResponseResult<List<Project>> getProjectInfoByOptions(@RequestParam Map<String,Object> options) throws MyException {
        ResponseResult<List<Project>> info = new ResponseResult<>();
        List<Project> projects = service.getProjectInfoByOptions(options);
        if (projects.size() != 0){
            info.setData(projects);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询结果为空");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.POST ,value = "/projects/upload")
    public ResponseResult<Boolean> getFileDataList(MultipartFile file, HttpServletRequest httpServletRequest) throws IOException, MyException, ParseException {
        ResponseResult<List<Object>> infoData = new ResponseResult<>();
        ResponseResult<Boolean> info = new ResponseResult<>();
        //上传文件
        boolean ishave = ImageUploadUtil.upload(file,httpServletRequest);
        if (ishave){
            //取值
            infoData = Excel.getExcelData(file,0);
            if(infoData.getData().isEmpty()||infoData.getData()==null){
                info.setMessage("获取数据失败！");
                info.setData(false);
                info.setCode(ResponseResult.ERROR);
            }
            //插入数据库
            for (int i = 0; i < infoData.getData().size(); i++) {
                Project project = new Project();
                HashMap<String,String> temp = (HashMap<String, String>) infoData.getData().get(i);
                project.setCompanyId(Double.valueOf(temp.get("建设单位id")).intValue());
                project.setManagementCompanyId(Double.valueOf(temp.get("管理单位id")).intValue());
                project.setProjectName(temp.get("项目名"));
                project.setAmount(new BigDecimal(temp.get("金额")));
                project.setPrincipal(temp.get("负责人"));
                project.setPrincipalPhone(temp.get("联系方式").substring(0, temp.get("联系方式").indexOf(".")));
                project.setCompanyType(temp.get("隶属行业"));
//                project.setFinalInspectionDate(new Date(temp.get("终检日期")));
                project.setFinalInspectionDate("".equals(temp.get("终检日期"))?null:new SimpleDateFormat("yyyy-MM-dd").parse(temp.get("终检日期")));
                project.setWarrantyDate("".equals(temp.get("质保日期"))?null:new SimpleDateFormat("yyyy-MM-dd").parse(temp.get("质保日期")));
                project.setProjectDes(temp.get("项目简介"));
                project.setManagement(temp.get("项目专管员"));
                project.setRemarks(temp.get("备注"));
                service.createProject(project);
            }
            info.setMessage("Success");
            info.setCode(ResponseResult.OK);
        }else{
            info.setMessage("Fail");
            info.setCode(ResponseResult.ERROR);
        }

        return  info;
    }
}
