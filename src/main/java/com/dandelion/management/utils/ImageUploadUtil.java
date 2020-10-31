package com.dandelion.management.utils;

import com.dandelion.management.bean.ImageFile;
import com.dandelion.management.service.ImageFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 基于commons-FileUpload
 * 文件上传
 * @author hongjianYang
 * @date 2020/8/18
 */
public class ImageUploadUtil {
    static Logger logger =LoggerFactory.getLogger(ImageUploadUtil.class);

    public static Boolean upload(MultipartFile file, HttpServletRequest req) throws IOException {
        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
            return false;
        }
        // 获取文件存储路径（绝对路径）
        String path = "D:/image/";
        // 获取原文件名
        String fileName = file.getOriginalFilename();
        // 创建文件实例
        File filePath = new File(path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            logger.debug("Create file catalogue upload image file to " + filePath);
        }
        // 写入文件
        logger.debug("Upload image file to " + filePath);
        file.transferTo(filePath);
        return true;
    }

}
