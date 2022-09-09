package com.example.expression;

import com.example.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: springsecurity
 * @ClassName SGExpressionRoot
 * @description: 自定义权限校验
 * @author: 徐杨顺
 * @create: 2022-09-08 20:39
 * @Version 1.0
 **/
@Component("ex")
public class SGExpressionRoot {

    public boolean hasAuthority(String authority) {
        //1获取当前登录人的权限
        //获取当前登录人员信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取权限
        List<String> permission = loginUser.getPermission();
        //2判断当前登录人员是否有权限
        boolean contains = permission.contains(authority);
        return contains;
    }
}
