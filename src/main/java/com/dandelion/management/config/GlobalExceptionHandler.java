package com.dandelion.management.config;

import com.dandelion.management.bean.ResponseResult;
import com.dandelion.management.bean.MyException;
import com.dandelion.management.utils.Final;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 *
 * @author hongjianYang
 * @date 2020/7/15
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ResponseResult<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        ResponseResult<String> r = new ResponseResult<>();
        r.setMessage(e.getMessage());
        r.setCode(Final.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}
