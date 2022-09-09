package com.example.handler;

import com.alibaba.fastjson.JSON;
import com.example.entity.ResponseResult;
import com.example.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: springsecurity
 * @ClassName AccessDeniedHandlerImpl
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-08 20:06
 * @Version 1.0
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //给前端返回一个ResponseResult 的json
        ResponseResult responseResult = new ResponseResult(HttpStatus.FORBIDDEN.value(), "没有权限，访问失败");
        String json = JSON.toJSONString(responseResult);
        WebUtils.renderString(response, json);
    }
}
