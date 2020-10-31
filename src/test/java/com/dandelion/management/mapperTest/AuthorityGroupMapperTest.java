package com.dandelion.management.mapperTest;

import com.dandelion.management.bean.AuthorityGroup;
import com.dandelion.management.mapper.AuthorityGroupMapper;
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
public class AuthorityGroupMapperTest {
    @Autowired
    private AuthorityGroupMapper mapper;

    @Test
    public void contextLoads() {
        AuthorityGroup authorityGroup = new AuthorityGroup();
        authorityGroup.setAuthorityGroupName("监理");
        mapper.insert(authorityGroup);
        System.out.println(mapper.selectList(null));
    }
}
