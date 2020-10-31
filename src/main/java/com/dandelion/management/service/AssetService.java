package com.dandelion.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dandelion.management.bean.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *  @author: Sun Qingxin
 *  @Date: 2020/7/27 19:49
 *  @Description:
 */
public interface AssetService extends IService<Asset> {

    /**
     * 直接查询所有信息
     * @return List 查询结果
     */
    public List<Asset> selectAll();

    /**
     * 根据id进行查询
     * @param idList id数组
     * @return List 查询结果
     */
    public List<Asset> selectByID(List idList);

    Asset getAssetInfoById(Integer assetId);

    /**
     * 根据条件进行查询
     * @param options 条件构造器
     * @return List 查询结果
     */
    public List<Asset> selectByQuery(Map<String, Object> options);

    /**
     * 插入
     * @param asset 插入对象
     * @return true/false
     */
    public boolean insert(Asset asset);

    /**
     * 更新
     * @param asset 对象
     * @return true/false
     */
    public boolean update(Asset asset);

    /**
     * 根据ID删除
     * @param asset_id
     * @return true/false
     */
    public boolean deleteByID(Integer asset_id);

    /**
     * 根据id批量删除
     * @param idList
     * @return true/false
     */
    public boolean deleteBatchIds(List idList);


}
