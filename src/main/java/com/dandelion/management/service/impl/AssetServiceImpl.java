package com.dandelion.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dandelion.management.bean.*;
import com.dandelion.management.mapper.AssetMapper;
import com.dandelion.management.mapper.ImplementMapper;
import com.dandelion.management.service.AssetService;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.StandardEmitterMBean;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  @author: Sun Qingxin
 *  @Date: 2020/8/1 15:26
 *  @Description:
 */
@Service
public class AssetServiceImpl extends ServiceImpl<AssetMapper, Asset> implements AssetService {

    @Autowired
    AssetMapper assetMapper;

    //final 常量
    public static final String IS_EMPTY = "";
    public static final String ASSET_ASSET_NAME = "assetName";
    public static final String ASSET_MAINTENANCE_UNIT = "maintenanceUnit";
    public static final String ASSET_TYPE = "assetType";
    public static final String ASSET_STATE= "assetState";
    public static final String MAINTENANCE_STATE = "maintainceState";


    /**
     * 显示全部数据
     * @Param
     * @return
     */
    @Override
    public List<Asset> selectAll() {

        List<Asset> result = new ArrayList<>();
        result = assetMapper.getAll();
        return result;
    }

    /**
     *  @author: Sun Qingxin
     *  @Date: 2020/8/1 16:29
     *  @Description:根据ID批量查询
     */
    @Override
    public List<Asset> selectByID(List idList) {
        List<Asset> result = assetMapper.selectBatchIds(idList);
        return result;
    }

    @Override
    public Asset getAssetInfoById(Integer assetId) {
        Asset asset;
        asset = assetMapper.selectById(assetId);
        if(asset != null){
            return asset;
        }else{
            return null;
        }
    }

    /**
     * 根据条件查询 //未完成
     * @param options
     * @return
     */
    @Override
    public List< Asset > selectByQuery(Map<String, Object> options) {
        QueryWrapper<Asset> queryWrapper = new QueryWrapper<Asset>();

        //资产名称  运维单位  资产类型  资产状态  维修情况
        if(options.get(ASSET_ASSET_NAME)!= null && !options.get(ASSET_ASSET_NAME).equals(IS_EMPTY)){
            //做模糊查询
            queryWrapper.like("asset_name",options.get(ASSET_ASSET_NAME));
        }
        if(options.get(ASSET_MAINTENANCE_UNIT ) != null && !options.get(ASSET_MAINTENANCE_UNIT ).equals(IS_EMPTY)){
            queryWrapper.eq("maintenance_unit" ,options.get(ASSET_MAINTENANCE_UNIT ));
        }
        if(options.get(ASSET_TYPE) != null && !options.get(ASSET_TYPE).equals(IS_EMPTY)){
            queryWrapper.eq("asset_type",options.get(ASSET_TYPE));
        }
        if (options.get(ASSET_STATE) != null && !options.get(ASSET_STATE).equals(IS_EMPTY)){
            queryWrapper.eq("asset_state",options.get(ASSET_STATE));
        }
        if (options.get(MAINTENANCE_STATE) != null && !options.get(MAINTENANCE_STATE).equals(IS_EMPTY)){
            queryWrapper.eq("maintaince_state",options.get(MAINTENANCE_STATE));
        }

        List<Asset> assetList = assetMapper.selectList(queryWrapper);
        return assetList;
    }

    @Override
    public boolean insert(Asset asset) {
        int result = assetMapper.insert(asset);
        //返回的是具体影响的条数
        if(result == 0 ){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean update(Asset asset) {
        int result = assetMapper.updateById(asset);
        //返回的是具体影响的条数
        if(result == 0 ){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 根据ID删除一条数据
     * @param asset_id
     * @return true 成功  false 失败
     */
    @Override
    public boolean deleteByID(Integer asset_id) {
        int result =  assetMapper.deleteById(asset_id);
        //返回的是具体影响的条数
        if(result == 0 ){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 根据idList 批量删除
     * @param idList
     * @return true 成功  false 失败
     */
    @Override
    public boolean deleteBatchIds(List idList) {
        int result = assetMapper.deleteBatchIds(idList);
        //返回的是具体影响的条数
        if(result == 0 ){
            return false;
        }else{
            return true;
        }
    }

}
