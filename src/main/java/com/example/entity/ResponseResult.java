package com.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @program: springsecurity
 * @ClassName ResponseResult
 * @description: 响应类
 * @author: 徐杨顺
 * @create: 2022-09-07 14:16
 * @Version 1.0
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    private Integer code;//状态码
    private String msg;//提示信息，如果有错误。前端可以获取该字段进行提示
    private T data;

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
