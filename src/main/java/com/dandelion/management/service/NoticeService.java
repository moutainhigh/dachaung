package com.dandelion.management.service;

import com.dandelion.management.bean.Announcement;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    /**
     * 获取单个公告
     * @param id
     * @return
     */
    Announcement selectById(Integer id);

    /**
     * 查询公告，根据Title、content和Author,如三者均为空，则表示查询所有
     * @param options 查询条件
     * @return 公告列表
     */
    List<Announcement> getAnnouncements(Map<String,Object> options) throws Exception;

    /**
     * 查询所有的公告
     * @return
     * @throws Exception
     */
    List<Announcement> findAll() throws Exception;

    /**
     * 根据ID删除公告
     * @param id
     * @return 执行成功返回true，否则false
     */
    Boolean deleteById(Integer id);

    /**
     * 根据List<Integer> 批量删除
     * @param list List<Integer>
     * @return Boolean类型，true删除成功，false删除失败
     */
    Boolean deleteBatch(List<Integer> list);

    /**
     * 保存公告
     * @param announcement 公告Bean
     * @return 保存成功返回true，否则false
     */
    Boolean save(Announcement announcement);

    Boolean update(Announcement announcement);

    /**
     * 更新公告
     * @param announcement 更新后的公告Bean， 公告ID不能修改
     * @return 修改成功返回true，否则false
     */
    Boolean updateById(Announcement announcement);
}
