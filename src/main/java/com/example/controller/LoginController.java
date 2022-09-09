package com.example.controller;

import com.example.entity.ResponseResult;
import com.example.entity.User;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @program: springsecurity
 * @ClassName LoginController
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-08 10:13
 * @Version 1.0
 **/

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    //登录接口
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }
    //登出接口
    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

    //测试接口，访问该接口需要demo权限
    @PostMapping("/user/demo")
    @PreAuthorize("hasAuthority('demo')")
    public ResponseResult demo(){
        return new ResponseResult(200,"demo");
    }
}
