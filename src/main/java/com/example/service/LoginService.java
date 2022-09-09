package com.example.service;

import com.example.entity.LoginUser;
import com.example.entity.ResponseResult;
import com.example.entity.User;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: springsecurity
 * @ClassName LoginService
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-08 10:15
 * @Version 1.0
 **/
@Service
public class LoginService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisCache redisCache;


    public ResponseResult login(User user) {
        //3使用ProviderManager auth方法进行验证
        //传入用户名密码，
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword(),null);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //校验失败了
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误！");
        }

        //4自己生成jwt给前端
        LoginUser loginUser= (LoginUser)(authenticate.getPrincipal());
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String,String> map=new HashMap();
        map.put("token",jwt);
        //5系统用户相关所有信息放入redis
        redisCache.setCacheObject("login:"+userId,loginUser);

        return new ResponseResult(200,"登陆成功",map);
    }


    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userId);

        return new ResponseResult(200,"退出成功！");
    }
}
