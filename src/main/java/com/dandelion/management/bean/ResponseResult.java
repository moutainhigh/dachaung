package com.dandelion.management.bean;

import lombok.Data;

/**
 * 包含自动异常处理机制的统一JSON返回值
 *
 * @author hongjianYang
 * @date 2020/7/15
 */
@Data
public class ResponseResult<T> {

    public static final Integer ERROR = 100;
    public static final Integer OK = 0;
    private Integer code;
    private String message;
    private String url;
    private T data;

}
