package com.dandelion.management.controller;

import com.dandelion.management.bean.ResponseResult;
import com.dandelion.management.bean.Implement;
import com.dandelion.management.bean.MyException;
import com.dandelion.management.service.ImplementService;
import com.dandelion.management.utils.Final;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sunqingxin
 */
@RestController
public class ImplementController {
    @Autowired
    ImplementService implementService;

    private ResponseResult<List<Implement>> getListResponseResult(ResponseResult<List<Implement>> info, List<Implement> implementList) {
        if(implementList != null){
            info.setData(implementList);
            info.setCode(ResponseResult.OK);
            info.setMessage("Success");
        }else{
            info.setCode(Final.ERROR);
            info.setMessage("Fail");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/implement/selectAllImplement")
    private ResponseResult<List<Implement>> selectAllImplement() throws MyException {
        ResponseResult<List<Implement>> info = new ResponseResult<>();
        List<Implement> implementList= implementService.selectAllImplement();
        return getListResponseResult(info,implementList);
    }

    @RequestMapping(method = RequestMethod.GET ,value = "/implement/selectByCompany")
    private ResponseResult<List<Implement>> selectByCompany(Integer companyId) throws MyException{
        ResponseResult<List<Implement>> info = new ResponseResult<>();
        List<Implement> implementList= implementService.selectByCompany(companyId);
        return getListResponseResult(info,implementList);
    }

    @RequestMapping(method = RequestMethod.GET ,value = "/implement/selectByAction")
    private ResponseResult<List<Implement>> selectByAction (Implement implement) throws MyException{
        ResponseResult<List<Implement>> info = new ResponseResult<>();
        List<Implement> implementList= implementService.selectByAction(implement);
        return getListResponseResult(info,implementList);
    }

    @RequestMapping(method = RequestMethod.GET , value = "/implement/insert")
    private ResponseResult<List<Implement>> insert(Implement implement) throws MyException{
        ResponseResult<List<Implement>> info = new ResponseResult<>();
        boolean res = implementService.insert(implement);
        List<Implement> list = new ArrayList<>();
        if (res){
            info.setMessage("Success");
            info.setCode(ResponseResult.OK);
        }else{
            info.setCode(ResponseResult.ERROR);
            info.setMessage("Fail");
        }
        info.setData(list);
        return info;
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/implement/deleteById")
    private ResponseResult<List<Implement>> deleteById(@RequestParam("id") Integer id) throws MyException{
        ResponseResult<List<Implement>> info = new ResponseResult<>();
        boolean res = implementService.deleteById(id);
        List<Implement> list = new ArrayList<>();
        list.add(implementService.getById(id));
        if(res){
            info.setCode(ResponseResult.OK);
            info.setMessage("Success");
        }else {
            info.setCode(ResponseResult.ERROR);
            info.setMessage("Fail");
        }
        info.setData(list);
        return info;
    }
}
