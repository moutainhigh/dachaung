package com.dandelion.management.mapperTest;

import com.dandelion.management.bean.Contract;
import com.dandelion.management.mapper.ContractMapper;
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
public class ContractMapperTest {
    @Autowired
    private ContractMapper mapper;

    @Test
    public void contextLoads() {
        Contract contract = new Contract();
        contract.setContractIntroduction("Test");
        mapper.insert(contract);
        System.out.println(mapper.selectList(null));
    }
}
