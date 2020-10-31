package com.dandelion.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dandelion.management.bean.Document;
import com.dandelion.management.mapper.DocumentMapper;
import com.dandelion.management.service.DocumentService;

import java.util.List;

/**
 * @author Sunqingxin
 */
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements DocumentService {

    @Override
    public List<Document> selectByAction(Document document) {
        return null;
    }

    @Override
    public List<Document> selectAll() {
        return null;
    }
}
