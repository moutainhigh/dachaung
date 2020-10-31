package com.dandelion.management.mapperTest;

import com.dandelion.management.bean.Company;
import com.dandelion.management.mapper.CompanyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/7/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyMapperTest {
    @Autowired
    private CompanyMapper mapper;

    @Test
    public void contextLoads() {
        Company company = new Company();
        company.setCompanyName("华为");
        mapper.insert(company);
        System.out.println(mapper.selectList(null));
    }
}
