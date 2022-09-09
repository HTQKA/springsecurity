package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springsecurity
 * @ClassName DemoController
 * @description: 测试控制器
 * @author: 徐杨顺
 * @create: 2022-09-06 14:46
 * @Version 1.0
 **/

@RestController
@RequestMapping("demo")
public class DemoController {
    @GetMapping("hello")
    @PreAuthorize("hasAuthority('dev:code:push')")
    public String hello() {
        return "Hello Spring Security";
    }

    @GetMapping("hello1")
    @PreAuthorize("hasAuthority('dev:code:reload1')")
    public String hello1() {
        return "XYSIMJ";
    }

    //调用自定义权限校验
    @GetMapping("123")
    @PreAuthorize("@ex.hasAuthority('dev:code:push')")
    public String XYS123() {
        return "123";
    }

}
