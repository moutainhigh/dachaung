package com.dandelion.management.service;

import com.dandelion.management.bean.Company;

import java.util.List;
import java.util.Map;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/7/27
 */
public interface CompanyService {

    public Company getCompanyById(Integer compamyId);

    List<Company> getCompanyInfoByOptions(Map<String, Object> options);

    public Boolean deleteCompany(Integer id);

    Boolean deleteByList(List<Integer> lists);

    public Boolean createCompany(Company company);

    public List<Company> getAllCompany();

    Boolean updateCompany(Company company);
}
