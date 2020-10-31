package com.dandelion.management.controller;

import com.dandelion.management.bean.*;
import com.dandelion.management.service.CompanyService;
import com.dandelion.management.utils.Final;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 公司控制器
 * 事件实体类必须下游接口之一
 * @author hongjianYang
 * @date 2020/7/27
 */
@RestController
public class CompanyController {

    @Autowired
    CompanyService service;

    @RequestMapping(method = RequestMethod.GET,value = "/company/{id}")
    public ResponseResult<Company> getCompangById(@PathVariable("id") Integer conpanyId) throws MyException {
        ResponseResult<Company> info = new ResponseResult<>();
        Company company = service.getCompanyById(conpanyId);
        if (company == null){
            throw new MyException("查询无此公司");
        }else{
            info.setCode(Final.OK);
            info.setData(company);
        }
        return info;
    }

    //搜索选择合同
    @RequestMapping(method = RequestMethod.GET,value = "/company/select")
    public ResponseResult<List<Company>> getEventInfoByOptions(@RequestParam Map<String,Object> options) throws MyException {
        ResponseResult<List<Company>> info = new ResponseResult<>();
        List<Company> companyInfo = service.getCompanyInfoByOptions(options);
        if (companyInfo.size() != 0){
            info.setData(companyInfo);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询结果为空");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/company/delete/{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable("id") Integer id) throws MyException {
        ResponseResult<Boolean> info = new ResponseResult<>();
        Boolean b = service.deleteCompany(id);
        //boolean b = true;
        if (!b) {
            throw new MyException("删除失败");
        } else {
            info.setCode(ResponseResult.OK);
            info.setData(b);
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/company/deletes")
    public ResponseResult<Boolean> deleteCompanyByList(@RequestParam("lists[]") final List<Integer> lists) throws MyException {
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

    @RequestMapping(method = RequestMethod.POST,value = "/company/create")
    public ResponseResult<Boolean> createCompany(@RequestBody Company company) throws MyException{
        ResponseResult<Boolean> ResponseResult = new ResponseResult<>();
        Boolean b = service.createCompany(company);
        if(!b){
            throw  new MyException("创建单位失败");
        } else {
            ResponseResult.setCode(ResponseResult.OK);
            ResponseResult.setData(b);
        }
        return ResponseResult;
    }


    @RequestMapping(method = RequestMethod.GET,value = "/company/all")
    public ResponseResult<List<Company>> getAllCompany() throws MyException {
        ResponseResult<List<Company>> info = new ResponseResult<>();
        List<Company> companys = service.getAllCompany();
        if (companys == null){
            throw new MyException("查询无公司");
        }else{
            info.setCode(Final.OK);
            info.setData(companys);
        }
        return info;
    }

    //更新
    @RequestMapping(method = RequestMethod.POST, value = "/company/update")
    public ResponseResult<Boolean> updateCompany(@RequestBody Company company) throws MyException {
        final ResponseResult<Boolean> info = new ResponseResult<>();
        final Boolean b = service.updateCompany(company);
        if (!b) {
            throw new MyException("数据更新失败,请查看数据类型是否正确");
        } else {
            info.setCode(Final.OK);
            info.setData(true);
        }
        return info;
    }
}
