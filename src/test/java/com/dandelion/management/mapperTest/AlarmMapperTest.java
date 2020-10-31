package com.dandelion.management.mapperTest;


import com.dandelion.management.bean.Alarm;
import com.dandelion.management.mapper.AlarmMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlarmMapperTest {
    @Autowired
    private AlarmMapper alarmMapper;

    @Test
    public void contextLoads() {
        Alarm alarm = new Alarm();
        alarm.setAlarmInfo("蛇蛇");
        alarmMapper.insert(alarm);
        System.out.println(alarmMapper.selectList(null));
    }

}
