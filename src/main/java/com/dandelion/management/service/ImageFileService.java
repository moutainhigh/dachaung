package com.dandelion.management.service;

import com.dandelion.management.bean.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/8/18
 */
public interface ImageFileService {
    /**
     * 图片文件上传
     * @param file  图片文件
     * @param req   HTTP请求体
     * @param image 图片数据信息
     * @return      是否成功上传
     */
    Boolean ImageFileUpload(MultipartFile file, HttpServletRequest req, ImageFile image) throws IOException;

    /**
     * 通过事件获取所有的已上传文件文件名
     * @param eventId   事件id
     * @param status    事件状态
     * @return 包含所有已上传文件名的List
     */
    List<String> GetAllImageFileNameByEnentId(Integer eventId,Integer status);
}
