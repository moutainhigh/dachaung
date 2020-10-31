package com.dandelion.management.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUploadUtil {
    static Logger logger = LoggerFactory.getLogger(ImageUploadUtil.class);

    public static Boolean upload(MultipartFile file, String fileType, String fileName) throws IOException {
        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
            return false;
        }
        // 获取文件存储路径（绝对路径）
        String path = "D:/" + fileType + "/";
        // 创建文件实例
        String oriName = file.getOriginalFilename();
        File filePath = new File(path, fileName + oriName.substring(oriName.indexOf(".")));
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            logger.debug("Create file catalogue upload file to " + filePath);
        }
        // 写入文件
        logger.debug("Upload file to " + filePath);
        file.transferTo(filePath);
        return true;
    }
}
