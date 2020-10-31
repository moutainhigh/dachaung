package com.dandelion.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dandelion.management.bean.Implement;

import java.util.List;

/**
 *
 * @author Sunqingxin
 */
public interface ImplementService extends IService<Implement> {
    /**
     * 得到所有的查询结果，管理员操作
     * @return
     */
    List<Implement> selectAllImplement();

    /**
     * 根据登录用户所在单位id获取查询结果
     * @param companyId 用户id
     * @return
     */
    List<Implement> selectByCompany(Integer companyId);

    /**
     * 根据条件进行查询
     * @param implement 实施表
     * @return
     */
    List<Implement> selectByAction(Implement implement);

    /**
     * 插入一条数据
     * @param implement 实体类
     * @return
     */
    boolean insert(Implement implement);

    /**
     * 通过id删除
     * @param id
     * @return
     */
    boolean deleteById(int id);

    /**
     * 根据id批量删除
     * @param list id数组
     * @return
     */
    boolean deleteBatchIds(List<Integer> list);

    /**
     * 更新
     * @param implement
     * @return
     */
    boolean update(Implement implement);

}
