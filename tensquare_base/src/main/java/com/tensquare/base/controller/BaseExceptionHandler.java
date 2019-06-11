package com.tensquare.base.controller;

import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 统一异常处理类
 * @Author Administrator
 * @Date 2019/6/11 15:07
 **/
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }
}
