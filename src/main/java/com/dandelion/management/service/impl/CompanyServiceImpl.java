package com.dandelion.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dandelion.management.bean.Company;
import com.dandelion.management.mapper.CompanyMapper;
import com.dandelion.management.service.CompanyService;
import com.dandelion.management.utils.Final;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/7/27
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private static final String CREDIT_CODE = "creditCode";

    private static final String COMPANY_NAME = "companyName";

    private static final String CORPORATION = "corporation";

    private static final String UNIT_TYPE = "unitType";



    private static final Integer UNDELETE = 0;

    private static final Integer UNUPDATE = 0;

    private static final Integer UNINSERT = 0;
    @Autowired
    CompanyMapper mapper;

    @Override
    public Company getCompanyById(Integer companyId){
        Company company = mapper.selectById(companyId);
        if (company == null) {
            return null;
        }else{
            return company;
        }
    }

    @Override
    public List<Company> getCompanyInfoByOptions(Map<String, Object> options) {
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();

        //信用代码
        if (options.get(CREDIT_CODE) != null && !Final.EMPTY_MSG.equals(options.get(CREDIT_CODE))){
            queryWrapper.like(Final.CREDIT_CODE_COLUMN,options.get(CREDIT_CODE));
        }

        //单位名称
        if (options.get(COMPANY_NAME) != null && !Final.EMPTY_MSG.equals(options.get(COMPANY_NAME))){
            queryWrapper.eq(Final.COMPANY_NAME_COLUMN,options.get(COMPANY_NAME));
        }

        //法定代表人
        if (options.get(CORPORATION) != null && !Final.EMPTY_MSG.equals(options.get(CORPORATION))){
            queryWrapper.like(Final.CORPORATION_COLUMN,options.get(CORPORATION));
        }

        //单位类型
        if (options.get(UNIT_TYPE) != null && !Final.EMPTY_MSG.equals(options.get(UNIT_TYPE))){
            queryWrapper.eq(Final.UNIT_TYPE_COLUMN,options.get(UNIT_TYPE));
        }


        List<Company> companyList = mapper.selectList(queryWrapper);
        return companyList;
    }

    @Override
    public Boolean deleteCompany(Integer id) {
        int result = mapper.deleteById(id);
        if (result == UNDELETE) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean deleteByList(List<Integer> lists) {
        Integer num = mapper.deleteBatchIds(lists);
        if (num != 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean createCompany(Company company) {
        int result = mapper.insert(company);

        if(result == UNINSERT){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<Company> getAllCompany() {
        List<Company> projects = mapper.selectList(null);
        if(projects.size() == 0){
            return null;
        }else{
            return projects;
        }
    }

    @Override
    public Boolean updateCompany(Company company) {
        int result = mapper.updateById(company);
        if (result == UNUPDATE) {
            return false;
        } else {
            return true;
        }
    }
}
