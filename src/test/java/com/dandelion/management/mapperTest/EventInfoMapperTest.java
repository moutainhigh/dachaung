package com.dandelion.management.mapperTest;

import com.dandelion.management.mapper.EventInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/7/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventInfoMapperTest {
    @Autowired
    EventInfoMapper mapper;

    @Test
    public void contextLoads() {
        System.out.println(mapper.selectList(null));
    }
}
