package com.dandelion.management.serviceTest;


import com.dandelion.management.bean.MyException;

import com.dandelion.management.service.AssetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssetServiceTest {
    @Autowired
    private AssetService assetService;


    @Test
    public void contextLoads() throws MyException {

//      System.out.println(assetService.SelectAll());
//        List idList = new ArrayList();
//        idList.add(1);
//        System.out.println(assetService.SelectByID(idList));

        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }

}
