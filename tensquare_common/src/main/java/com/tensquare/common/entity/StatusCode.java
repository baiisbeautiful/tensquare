package com.tensquare.common.entity;

/**
 * @Description 返回码定义类
 * @Author Administrator
 * @Date 2019/6/11 10:15
 **/
public class StatusCode {
    public static final int OK=20000;//成功
    public static final int ERROR=20001;//失败
    public static final int LOJINERROR=20002;//用户名或密码错误
    public static final int ACCESSERROR=20003;//权限不足
    public static final int REMOTEERROR=20004;//远程调用失败
    public static final int REPERROR=20005;//重复操作

}
