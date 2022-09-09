package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.entity.LoginUser;
import com.example.entity.User;
import com.example.mapper.MenuMapper;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @program: springsecurity
 * @ClassName UserDetialService
 * @description:
 * @author: 徐杨顺
 * @create: 2022-09-07 15:50
 * @Version 1.0
 **/
@Service
public class UserDetailServiceImpl  implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.根据用户名从数据库中获取用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);//查询条件
        User user = userMapper.selectOne(wrapper);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名不存在");
        }
        //2.TODO 查询权限信息
        //List<String> list = new ArrayList<>(Arrays.asList("hello", "123"));
        //从数据库中查询权限
        List<String> perms = menuMapper.selectPermsByUserId(user.getId());
        //3.返回一个UserDetails，用户信息和权限信息存入LoginUser对象中
        return new LoginUser(user,perms);
    }
}
