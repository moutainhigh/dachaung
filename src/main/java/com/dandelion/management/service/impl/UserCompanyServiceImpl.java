package com.dandelion.management.service.impl;

import com.dandelion.management.bean.UserCompany;
import com.dandelion.management.mapper.UserCompanyMapper;
import com.dandelion.management.service.UserCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/8/16
 */
@Service
public class UserCompanyServiceImpl implements UserCompanyService {
    @Autowired
    UserCompanyMapper mapper;

    @Override
    public List<UserCompany> getAllUserCompany() {
        return mapper.selectByMap(null);
    }
}
