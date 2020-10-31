package com.dandelion.management.controller;

import com.dandelion.management.bean.ResponseResult;
import com.dandelion.management.bean.MyException;
import com.dandelion.management.bean.User;
import com.dandelion.management.service.UserService;
import com.dandelion.management.utils.Final;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制器
 * 事件实体类必须下游接口之一
 * @author hongjianYang
 * @date 2020/7/27
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;
    /**
     * 根据用户id返回用户全部信息(暂未进行脱敏)
     * @param userId 用户表主键
     * @return 用户实体
     */
    @RequestMapping("/user/{id}")
    public ResponseResult<User>getUserById(@PathVariable("id") Integer userId) throws MyException {
        ResponseResult<User> info = new ResponseResult<>();
        User user = userService.selectById(userId);
        if (user == null){
            throw new MyException("无此用户信息");
        }else{
            info.setCode(Final.OK);
            info.setData(user);
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/user/all")
    public ResponseResult<List<User>> getAllUser() throws MyException{
        ResponseResult<List<User>> info = new ResponseResult<>();
        List<User> users = userService.getAllUser();
        if (users != null) {
            info.setData(users);
            info.setCode(ResponseResult.OK);
        }else{
            throw new MyException("查询单位数据失败");
        }

        return info;
    }

}
