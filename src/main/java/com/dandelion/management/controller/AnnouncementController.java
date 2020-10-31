package com.dandelion.management.controller;

import com.dandelion.management.bean.Announcement;
import com.dandelion.management.bean.Contract;
import com.dandelion.management.bean.ResponseResult;
import com.dandelion.management.bean.MyException;
import com.dandelion.management.service.NoticeService;
import com.dandelion.management.utils.Final;
import com.dandelion.management.utils.ImageUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 事件类的Restful API
 *
 * @author Liang
 * @version 2020/8/22
 * @date 2020/8/20
 */
@RestController("/notice")
@RequestMapping("/notice")
public class AnnouncementController {
    @Autowired
    NoticeService service;

    /**
     * 查看公告内容
     * @param announcementId  公告ID
     * @return 封装的ResponseResult对象
     * @throws MyException
     */
    @RequestMapping(method= RequestMethod.GET,value = "/announcement/{id}")
    public ResponseResult<Announcement> getAnnouncementById(@PathVariable("id") Integer announcementId) throws MyException{
        ResponseResult<Announcement> info = new ResponseResult<>();
        Announcement announcement = service.selectById(announcementId);
        if(announcement == null){
            throw new MyException("未查询到公告");
        }
        else {
            info.setCode(Final.OK);
            info.setData(announcement);
        }
        return info;
    }

    /**
     * 根据ID删除公告
     * @param id
     * @return
     * @throws MyException
     */
    @RequestMapping(method=RequestMethod.POST, value = "/delete/{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable(value="id") Integer id) throws MyException{
        ResponseResult<Boolean> info = new ResponseResult<Boolean>();
        Boolean result = new Boolean(true);
        result = service.deleteById(id);
        if(!result){
            throw new MyException("删除公告失败！");
        }else {

            info.setCode(ResponseResult.OK);
            info.setData(result);
        }
        return info;
    }

    /**
     * 添加公告
     * @param announcement 公告Bean
     * @return ResponseResult对象
     * @throws MyException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseResult<Boolean> addAnnouncement(@RequestBody Announcement announcement) throws MyException{
        ResponseResult<Boolean> info = new ResponseResult<Boolean>();
        Boolean result = service.save(announcement);
        if(!result){
            throw  new MyException("添加公告失败");
        } else {
            info.setCode(Final.OK);
            info.setData(result);
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public ResponseResult<Boolean> updateAnnouncement(@RequestBody Announcement announcement) throws MyException {
        ResponseResult<Boolean> info = new ResponseResult<>();
        Boolean result = service.update(announcement);
        if (!result) {
            throw new MyException("数据更新失败,请查看数据类型是否正确");
        } else {
            info.setCode(ResponseResult.OK);
            info.setData(result);
        }
        return info;
    }
    /**
     * 获得所有的公告
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/find")
    public ResponseResult<List<Announcement>> findAnnouncement() throws Exception{
        ResponseResult<List<Announcement>> info = new ResponseResult<List<Announcement>>();
        List<Announcement> list = null;
        try {
            list = service.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(list == null){
            throw new MyException("当前无公告信息！");
        }else {
            info.setData(list);
            info.setCode(Final.OK);
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.POST, value="/deletes")
    public ResponseResult<Boolean> deleteByBatchId(@RequestParam("lists[]") final List<Integer> lists) throws MyException{
        final ResponseResult<Boolean> info = new ResponseResult<Boolean>();
        final Boolean result = service.deleteBatch(lists);
        if(result){
            info.setCode(Final.OK);
            info.setData(true);
        }else{
            throw new MyException("批量删除失败!");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.GET, value="/search")
    public ResponseResult<List<Announcement>> searchAnnouncement(Announcement announcement) throws MyException{
        ResponseResult<List<Announcement>> info = new ResponseResult<List<Announcement>>();
        String title = announcement.getAnnouncementTitle();
        String content = announcement.getContent();
        Integer author = announcement.getPublisherId();
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("content",content);
        map.put("announcement_title",title);
        map.put("publisher_id",author);
        List<Announcement> list = null;
        try{
            list = service.getAnnouncements(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        if( list == null){
            throw new MyException("当前暂无公告");
        }else {
            info.setCode(Final.OK);
            info.setData(list);
        }
        return info;
    }

    //待修改
    private ResponseResult<Boolean> uplodeFile(MultipartFile file, HttpServletRequest httpServletRequest) throws IOException, MyException{
        ResponseResult<Boolean> info = new ResponseResult<>();
        boolean ishave = ImageUploadUtil.upload(file,httpServletRequest);
        if (ishave == true) {
            info.setCode(Final.OK);
            info.setData(ishave);
            info.setMessage("上传成功");
        } else {
            throw new MyException("上传失败");
        }
        return info;
    }

}
