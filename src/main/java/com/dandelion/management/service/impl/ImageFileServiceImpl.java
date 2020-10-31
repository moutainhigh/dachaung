package com.dandelion.management.service.impl;

import com.dandelion.management.bean.ImageFile;
import com.dandelion.management.mapper.ImageFileMapper;
import com.dandelion.management.service.ImageFileService;
import com.dandelion.management.utils.ImageUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/8/18
 */
@Service
public class ImageFileServiceImpl implements ImageFileService {
    @Autowired
    ImageFileMapper mapper;

    /**
     * 图片文件上传
     *
     * @param file  图片文件
     * @param req   HTTP请求体
     * @param image 图片数据信息
     * @return 是否成功上传
     */
    @Override
    public Boolean ImageFileUpload(MultipartFile file, HttpServletRequest req, ImageFile image) throws IOException {
        Boolean res = ImageUploadUtil.upload(file, req);
        if (res) {
            image.setRemark(file.getOriginalFilename());
            image.setUploadTime(new Timestamp(System.currentTimeMillis()));
            Integer sqlResult = mapper.insert(image);
            if (sqlResult != 0) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * 通过事件获取所有的已上传文件文件名
     *
     * @param eventId   事件id
     * @param status    事件状态
     * @return 包含所有已上传文件名的List
     */
    @Override
    public List<String> GetAllImageFileNameByEnentId(Integer eventId,Integer status) {
        List<String> imageFileNames = new ArrayList<>();
        Map<String,Object> option = new HashMap<>(16);
        option.put("event_id",eventId);
        option.put("event_status",status);
        for (ImageFile e : mapper.selectByMap(option)) {
            if (e.getRemark()!=null && !e.getRemark().equals("")){
                imageFileNames.add(e.getRemark());
            }
        }
        return imageFileNames;
    }
}
