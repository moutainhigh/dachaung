package com.dandelion.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dandelion.management.bean.Implement;
import com.dandelion.management.mapper.ImplementMapper;
import com.dandelion.management.service.ImplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实施具体实现业务
 * @author Sunqingxin
 */
@Service
public class ImplementServiceImpl extends ServiceImpl<ImplementMapper,Implement> implements ImplementService {
    @Autowired
    ImplementMapper implementMapper;
    @Override
    public List<Implement> selectAllImplement() {
        List<Implement> result= implementMapper.getAll();
        return result;
    }

    @Override
    public List<Implement> selectByCompany(Integer companyId) {
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("company_id",companyId);
        List<Implement> result = implementMapper.selectByMap(columnMap);
        return result;
    }

    @Override
    public List<Implement> selectByAction(Implement implement) {
        QueryWrapper<Implement> implementQueryWrapper = new QueryWrapper<>(implement);
        List<Implement> result = implementMapper.selectList(implementQueryWrapper);
        return result;
    }

    @Override
    public boolean insert(Implement implement) {
        int result = implementMapper.insert(implement);
        if(result != 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) {
        int result = implementMapper.deleteById(id);
        if(result != 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteBatchIds(List<Integer> list) {
        int result = implementMapper.deleteById((Serializable) list);
        if(result != 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean update(Implement implement) {
        QueryWrapper<Implement> implementQueryWrapper = new QueryWrapper<>(implement);
        int result = implementMapper.update(implement,implementQueryWrapper);
        if(result != 0){
            return true;
        }else{
            return false;
        }
    }
}
