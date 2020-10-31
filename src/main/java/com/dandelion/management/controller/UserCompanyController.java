package com.dandelion.management.controller;

import com.dandelion.management.bean.ResponseResult;
import com.dandelion.management.bean.MyException;
import com.dandelion.management.bean.UserCompany;
import com.dandelion.management.service.UserCompanyService;
import com.dandelion.management.utils.Final;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/8/16
 */
@RestController
public class UserCompanyController {
    @Autowired
    UserCompanyService service;

    @RequestMapping(method = RequestMethod.GET,value = "/userCompany/all")
    public ResponseResult<List<UserCompany>> getAllUserCompany() throws MyException {
        ResponseResult<List<UserCompany>> info = new ResponseResult<>();
        List<UserCompany> userCompanies = service.getAllUserCompany();
        if (userCompanies != null){
            info.setCode(Final.OK);
            info.setData(userCompanies);
        }else{
            throw new MyException("无数据返回");
        }
        return info;
    }
}
