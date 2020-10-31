package com.dandelion.management;

import com.dandelion.management.mapper.AuthorityMapper;
import com.dandelion.management.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagementApplicationTests {

    @Autowired
    AuthorityMapper authMapper;

    @Test
    public void contextLoads() {
        String[] tests = "1,2,3,4,5".split(",");
        List<String> array = Arrays.asList(tests);

        System.out.println(authMapper.selectBatchIds(array));
    }
}
