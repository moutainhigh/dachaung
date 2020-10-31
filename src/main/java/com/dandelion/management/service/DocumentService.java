package com.dandelion.management.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dandelion.management.bean.Document;

import java.util.List;

/**
 * @author Sunqingxin
 */
public interface DocumentService extends IService<Document> {
    /**
     * 通过事件进行查询
     * @param document
     * @return
     */
    List<Document> selectByAction(Document document);

    /**
     * 查询所有
     * @return
     */
    List<Document> selectAll();



}
