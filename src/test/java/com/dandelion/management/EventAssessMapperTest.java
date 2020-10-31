package com.dandelion.management;

import com.dandelion.management.mapper.EventAssessMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/8/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventAssessMapperTest {
    @Autowired
    EventAssessMapper mapper;

    @Test
    public void contextLoads() {
        System.out.println(mapper.selectByMap(null));
    }
}
