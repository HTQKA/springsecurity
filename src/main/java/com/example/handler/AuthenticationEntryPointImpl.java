package com.example.handler;

import com.alibaba.fastjson.JSON;
import com.example.entity.ResponseResult;
import com.example.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: springsecurity
 * @ClassName AuthenticationEntryPointImpl
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-08 20:01
 * @Version 1.0
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //给前端返回一个ResponseResult 的json
        ResponseResult responseResult = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "您的登录认证失败了，请从新登录");
        String json = JSON.toJSONString(responseResult);
        WebUtils.renderString(response, json);
    }
}
