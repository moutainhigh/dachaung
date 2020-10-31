package com.dandelion.management.controller;

import com.dandelion.management.bean.ResponseResult;
import com.dandelion.management.bean.ImageFile;
import com.dandelion.management.bean.MyException;
import com.dandelion.management.service.ImageFileService;
import com.dandelion.management.utils.Final;
import com.dandelion.management.utils.ImageUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Image File upload controller
 *
 * @author hongjian Yang
 * @date 2020/8/18
 */
@RestController
public class ImageFileUploadController {
    @Autowired
    ImageFileService service;
    /**
     * 文件上传试验方法 (已废弃)
     * @param file  文件
     * @param req   HTTP请求体
     * @return
     * @throws IOException
     * @throws MyException
     */
    @Deprecated
    public ResponseResult<Boolean> uploadImage(MultipartFile file, HttpServletRequest req) throws IOException, MyException {
        ResponseResult<Boolean> info = new ResponseResult<>();
        Boolean res = ImageUploadUtil.upload(file,req);
        if (res){
            info.setCode(Final.OK);
            info.setData(res);
            info.setMessage("上传成功");
        }else{
            throw new MyException("上传失败");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/image/upload")
    public ResponseResult<Boolean> uploadImageFile(MultipartFile file, HttpServletRequest req, ImageFile image) throws IOException, MyException {
        ResponseResult<Boolean> info = new ResponseResult<>();
        Boolean res = service.ImageFileUpload(file,req,image);
        if (res == true) {
            info.setCode(Final.OK);
            info.setData(res);
            info.setMessage("上传成功");
        }else{
            throw new MyException("上传失败");
        }
        return info;
    }

    @GetMapping("/image/{status}/{eventId}")
    public ResponseResult<List<String>> getAllImageByEventId(@PathVariable("eventId") Integer eventId,@PathVariable("status")
                                                        Integer status) throws MyException {
        ResponseResult<List<String>> info = new ResponseResult<>();
        List<String> data = service.GetAllImageFileNameByEnentId(eventId,status);
        if (data != null){
            info.setData(data);
            info.setCode(Final.OK);
        }else{
            throw new MyException("无文件");
        }
        return info;
    }
}
