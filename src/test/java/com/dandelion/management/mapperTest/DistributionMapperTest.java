package com.dandelion.management.mapperTest;

import com.dandelion.management.mapper.DistributionMapper;
import com.dandelion.management.bean.Distribution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributionMapperTest {
    @Autowired
    private DistributionMapper mapper;

    @Test
    public void contextLoads() {
        Distribution distribution = new Distribution();
        distribution.setAssetId(001);
        distribution.setInspectionsId(002);
        mapper.insert(distribution);
        System.out.println(mapper.selectList(null));
    }
}