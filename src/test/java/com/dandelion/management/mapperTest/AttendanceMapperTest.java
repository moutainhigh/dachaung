package com.dandelion.management.mapperTest;

import com.dandelion.management.bean.Attendance;
import com.dandelion.management.mapper.AttendanceMapper;
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
public class AttendanceMapperTest {
    @Autowired
    private AttendanceMapper mapper;

    @Test
    public void contextLoads() {
        Attendance attendance = new Attendance();
        attendance.setRemark("good~");
        mapper.insert(attendance);
        System.out.println(mapper.selectList(null));
    }
}
