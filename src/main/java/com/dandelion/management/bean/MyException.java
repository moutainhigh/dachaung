package com.dandelion.management.bean;

/**
 * 全局异常处理类
 *
 * @author hongjianYang
 * @date 2020/7/15
 */
public class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}
