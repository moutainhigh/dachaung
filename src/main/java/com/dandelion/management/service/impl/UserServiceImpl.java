package com.dandelion.management.service.impl;

import com.dandelion.management.bean.Company;
import com.dandelion.management.bean.Project;
import com.dandelion.management.bean.User;
import com.dandelion.management.mapper.UserMapper;
import com.dandelion.management.service.UserService;
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
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User selectById(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user != null){
            return user;
        }
        else{
            return null;
        }
    }


    @Override
    public List<User> getAllUser() {
        List<User> users = userMapper.selectList(null);
        if(users.size() == 0){
            return null;
        }else{
            return users;
        }
    }

    @Override
    public List<User> selectByQuery(Map<String, Object> options) {
        //姓名、账号、状态、单位

        return null;
    }

    @Override
    public boolean update(User user) {
        //需要传id主键
        int result = userMapper.updateById(user);
        if(result == 0 ){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean deleteByID(Integer userID) {
        int result = userMapper.deleteById(userID);
        if(result == 0 ){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean deleteBatchIds(List idList) {
        int result = userMapper.deleteBatchIds(idList);
        if(result == 0 ){
            return false;
        }else{
            return true;
        }
    }
}
