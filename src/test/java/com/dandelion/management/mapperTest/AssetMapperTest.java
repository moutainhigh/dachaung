package com.dandelion.management.mapperTest;

import com.dandelion.management.bean.Asset;
import com.dandelion.management.mapper.AssetMapper;
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
public class AssetMapperTest {
    @Autowired
    private AssetMapper mapper;

    @Test
    public void contextLoads() {
//        Asset asset = new Asset();
//        asset.setAssetName("交换机");
//        mapper.insert(asset);
//        System.out.println(mapper.selectList(null));
        System.out.println(mapper.getAll());
    }
}
