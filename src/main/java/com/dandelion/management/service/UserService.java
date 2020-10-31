package com.dandelion.management.service;

import com.dandelion.management.bean.Company;
import com.dandelion.management.bean.User;

import java.util.List;
import java.util.Map;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/7/27
 */
public interface UserService {
    /**
     * 通过id查用户
     * @param userId
     * @return
     */
    User selectById(Integer userId);

    /**
     * 得到所有的用户。
     * @return
     */
    List<User> getAllUser();

    /**
     * 条件查询
     * @param options
     * @return
     */
    List<User> selectByQuery(Map<String, Object> options);

    /**
     * 更新
     * @param user
     * @return
     */
    boolean update(User user);

    /**
     * 通过ID删除
     * @param userID
     * @return
     */
    boolean deleteByID(Integer userID);

    /**
     * 根据id批量删除
     * @param idList
     * @return true/false
     */
    public boolean deleteBatchIds(List idList);

}
