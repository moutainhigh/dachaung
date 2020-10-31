package com.dandelion.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.dandelion.management.bean.Announcement;
import com.dandelion.management.mapper.AnnouncementMapper;
import com.dandelion.management.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class NoticeServiceImpl implements NoticeService {
    /**
     * 未删除
     */
    private static final Integer UNDELETE = 0;
    /**
     * 未更新
     */
    private static final Integer UNUPDATE = 0;
    /**
     * 未插入
     */
    private static final Integer UNINSERT = 0;
    @Autowired
    AnnouncementMapper mapper;

    @Override
    public Announcement selectById(Integer id) {
        Announcement announcement = mapper.selectById(id);

        return announcement;
    }

    @Override
    public List<Announcement> getAnnouncements(Map<String, Object> options) throws Exception {
        //List<Announcement> list = mapper.selectByMap(options);
        String title = (String)options.get("announcement_title");
        String content = (String)options.get("content");
        Integer author = (Integer) options.get("publisher_id");
        List<Announcement> list = mapper.selectList(new QueryWrapper<Announcement>().like("announcement_title",title).
                like("content",content).like("publisher_id",author));
        return list;
    }

    @Override
    public List<Announcement> findAll() throws Exception {
        List<Announcement> list = mapper.selectList(null);
        if (list.size() == 0)
            return null;
        else
            return list;
    }

    @Override
    public Boolean deleteById(Integer id) {
        int result = mapper.deleteById(id);
        if(result == UNDELETE)
            return false;
        return true;
    }

    @Override
    public Boolean deleteBatch(List<Integer> list) {
        Integer count = mapper.deleteBatchIds(list);
        if(count == list.size())
            return true;
        return false;
    }

    @Override
    public Boolean save(Announcement announcement) {

        int result = mapper.insert(announcement);
        if( result == UNINSERT)
            return false;
        return true;
    }

    @Override
    public Boolean update(Announcement announcement) {
        int result = mapper.updateById(announcement);
        if (result == UNUPDATE) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean updateById(Announcement announcement) {
        int result = mapper.updateById(announcement);
        if(result == UNUPDATE)
            return false;
        return true;
    }
}
