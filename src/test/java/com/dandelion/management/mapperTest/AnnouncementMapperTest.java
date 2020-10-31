package com.dandelion.management.mapperTest;


import com.dandelion.management.bean.Announcement;
import com.dandelion.management.mapper.AnnouncementMapper;
import com.dandelion.management.service.NoticeService;
import com.dandelion.management.service.impl.NoticeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Class description
 *
 * @author hongjianYang
 * 2020/7/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnouncementMapperTest {
    //@Autowired
    private AnnouncementMapper mapper;
    @Autowired
    private NoticeService service;
    @Test
    public void contextLoads() throws Exception {
       /*
        Announcement announcement = new Announcement();
        announcement.setAnnouncementTitle("title1");
        announcement.setContent("content");
        announcement.setPublisherId(1);
        announcement.setRemarks("memo");
        announcement.setReleaseTime(new Date());
        mapper.insert(announcement);
        System.out.println(mapper.selectList(null));

        */
        //mapper.deleteById(3);
        /*
        Announcement announcement = mapper.selectById(2);
        announcement.setReleaseTime(new Date());
        announcement.setAnnouncementTitle("HEllo");
        announcement.setContent("Hello, Context");
        mapper.updateById(announcement);
        announcement = mapper.selectById(2);

         */

        /*
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("title","");
        map.put("content","");
        map.put("author","");
        try{
            List<Announcement> list = service.getAnnouncements(map);
            for(Announcement announcement:list)
                System.out.println(announcement);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        */

        List<Announcement> list = service.findAll();
        for(Announcement announcement:list)
            System.out.println(announcement);

    }
}
