package com.dandelion.management.mapperTest;

import com.dandelion.management.bean.Assess;
import com.dandelion.management.mapper.AssessMapper;
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
public class AssessMapperTest {
    @Autowired
    private AssessMapper mapper;

    @Test
    public void contextLoads() {
        Assess assess = new Assess();
        assess.setComment("Yes");
        mapper.insert(assess);
        System.out.println(mapper.selectList(null));
    }
}
