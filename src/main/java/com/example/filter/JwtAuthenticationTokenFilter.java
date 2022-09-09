package com.example.filter;

import com.example.entity.LoginUser;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Objects;

/**
 * @program: springsecurity
 * @ClassName JwtAuthenticationTokenFilter
 * @description:自定一过滤器，并在Security配置类中将这个自定一过滤器配加入的security过滤链的最前面
 * @author: 徐杨顺
 * @create: 2022-09-08 13:34
 * @Version 1.0
 **/
//OncePerRequestFilter,直走一次，在请求前
@Component
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启全局方法安全校验，在方法允许前进行权限校验，需要在方法前加@PreAuthorize("hasAuthority = '权限'")
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getRequestURL());
        //1获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行，让后面的过滤器执行
            filterChain.doFilter(request, response);
            return;
        }
        //2解析token
        String id;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            id = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token不合法");
        }

        //3获取userId
        LoginUser loginUser = redisCache.getCacheObject("login:" + id);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("当前用户未登录");
        }
        //4封装Authentication
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        //5存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行，让后面的过滤器执行
        filterChain.doFilter(request, response);
    }
}
