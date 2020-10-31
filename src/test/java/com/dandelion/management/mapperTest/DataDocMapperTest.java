package com.dandelion.management.mapperTest;

import com.dandelion.management.bean.DataDoc;
import com.dandelion.management.mapper.DataDocMapper;
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
public class DataDocMapperTest {
    @Autowired
    private DataDocMapper mapper;

    @Test
    public void contextLoads() {
        DataDoc dataDoc = new DataDoc();
        dataDoc.setDocName("Test");
        mapper.insert(dataDoc);
        System.out.println(mapper.selectList(null));
    }
}
