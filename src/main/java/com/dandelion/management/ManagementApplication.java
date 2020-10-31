package com.dandelion.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.dandelion.management.mapper")
@SpringBootApplication
/**
 * SpringBoot Application 启动类
 *
 * @author hongjianYang
 * @date 2020/07/08
 */
public class ManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
    }

}
